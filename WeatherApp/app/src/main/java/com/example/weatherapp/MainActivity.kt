package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.network.WeatherService
import com.google.android.gms.location.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var tv_main: TextView
    private lateinit var tv_main_description: TextView
    private lateinit var tv_temp: TextView
    private lateinit var tv_sunrise_time: TextView
    private lateinit var tv_sunset_time: TextView
    private lateinit var tv_speed_unit: TextView
    private lateinit var tv_min: TextView
    private lateinit var tv_max: TextView
    private lateinit var tv_country: TextView
    private lateinit var tv_name: TextView
    private lateinit var mFusedLocationClient: FusedLocationProviderClient


    private var mProgressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_main = findViewById(R.id.tv_main)
        tv_main_description = findViewById(R.id.tv_main_description)
        tv_temp = findViewById(R.id.tv_temp)
        tv_sunrise_time = findViewById(R.id.tv_sunrise_time)
        tv_sunset_time = findViewById(R.id.tv_sunset_time)
        tv_speed_unit = findViewById(R.id.tv_speed)
        tv_min = findViewById(R.id.tv_min)
        tv_max = findViewById(R.id.tv_max)
        tv_country = findViewById(R.id.tv_country)
        tv_name = findViewById(R.id.tv_name)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if(!isLocationEnabled()){
            Toast.makeText(
                this,
                "Your location provider is turned off",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }else{
            Dexter.withActivity(this)
                .withPermissions(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        if (report!!.areAllPermissionsGranted()) {
                            // TODO (STEP 7: Call the location request function here.)
                            // START
                            requestLocationData()
                            // END
                        }

                        if (report.isAnyPermissionPermanentlyDenied) {
                            Toast.makeText(
                                this@MainActivity,
                                "You have denied location permission. Please allow it is mandatory.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<PermissionRequest>?,
                        token: PermissionToken?
                    ) {
                        showRationalDialogForPermissions()
                    }

                }).onSameThread()
                .check()
        }
    }

    private fun getLocationWeatherDetails(latitude: Double, longitude: Double){
        if(Constants.isNetworkAvailable(this)){
            val retrofit : Retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service : WeatherService = retrofit
                .create<WeatherService>(WeatherService::class.java)

            val listCall: Call<WeatherResponse> = service.getWeather(
                latitude, longitude, Constants.METRIC_UNIT, Constants.APP_ID
            )

            showCustomProgressDialog()

            listCall.enqueue(object : Callback<WeatherResponse>{
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if(response.isSuccessful){
                        hideProgressDialog()
                        val weatherList: WeatherResponse? = response.body()
                        if (weatherList != null) {
                            setupUI(weatherList)
                        }
                        Log.i("Response Result", "$weatherList")
                    }else{
                        when(response.code()){
                            400 -> {Log.e("Error 404", "Bad connection")}
                        }
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    hideProgressDialog()
                    Log.i("Failure", t.message.toString())
                }

            })
        }else{
            Toast.makeText(this@MainActivity, "You have Internet connection", Toast.LENGTH_SHORT)
        }
    }

    private fun showRationalDialogForPermissions() {
        AlertDialog.Builder(this)
            .setMessage("It Looks like you have turned off permissions required for this feature. It can be enabled under Application Settings")
            .setPositiveButton(
                "GO TO SETTINGS"
            ) { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            .setNegativeButton("Cancel") { dialog,
                                           _ ->
                dialog.dismiss()
            }.show()
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationData() {

        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private fun isLocationEnabled(): Boolean {

        // This provides access to the system location services.
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            val latitude = mLastLocation.latitude
            Log.i("Current Latitude", "$latitude")

            val longitude = mLastLocation.longitude
            Log.i("Current Longitude", "$longitude")
            getLocationWeatherDetails(latitude, longitude)
        }
}
    private fun showCustomProgressDialog(){
        mProgressDialog = Dialog(this)
        mProgressDialog!!.setContentView(R.layout.dialog_custom_progress)
        mProgressDialog!!.show()
    }

    private fun hideProgressDialog(){
        if(mProgressDialog !=null){
            mProgressDialog!!.dismiss()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setupUI(weatherList: WeatherResponse){
        for(i in weatherList.weather.indices){
            Log.i("Weather Name: ", weatherList.weather.toString())
            tv_main.text  = weatherList.weather[i].main
            tv_main_description.text = weatherList.weather[i].description
            tv_temp.text = weatherList.main.temp.toString() + getUnit(application.resources.configuration.locales.toString())

            tv_min.text = weatherList.main.temp_min.toString() + " min"
            tv_max.text = weatherList.main.temp_max.toString() + " max"

            tv_speed_unit.text = weatherList.wind.speed.toString()

            tv_country.text = weatherList.sys.country
            tv_name.text = weatherList.name

            tv_sunrise_time.text = unixTime(weatherList.sys.sunrise)
            tv_sunset_time.text = unixTime(weatherList.sys.sunset)
        }

    }

    private fun getUnit(value: String) : String?{
        var value = "C"
        if("US" == value){
            value = "F"
        }
        return value;
    }

    private fun unixTime(timex: Long): String?{
        val date = Date(timex *1000L)
        val sdf = SimpleDateFormat("HH:mm", Locale.US)
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date);
    }

}