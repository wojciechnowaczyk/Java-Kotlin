package com.example.androidarchitectures.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidarchitectures.model.CountriesResponse
import com.example.androidarchitectures.model.CountriesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesViewModel : ViewModel {
    private var countriesList : MutableLiveData<List<String>> = MutableLiveData<List<String>>()
    private lateinit var service: CountriesService;
    constructor(){
        service = CountriesService();
        fetchCountries();
    }

    public fun getCountries(): LiveData<List<String>> {
        return countriesList
    }


    private fun fetchCountries() {

        service.getCountries()

        service.getCountries()?.enqueue(object : Callback<List<CountriesResponse>> {
            override fun onResponse(
                call: Call<List<CountriesResponse>>,
                response: Response<List<CountriesResponse>>
            ) {
                if (response.isSuccessful) {
                    val countries = response.body()
                    val countryNames : ArrayList<String> = ArrayList();
                    if (countries != null) {
                        for (country in countries){
                            countryNames.add(country.name.common)
                        }
                        countriesList.value = countryNames
                    }
                } else {
                    val code = response.code()
                }
            }

            override fun onFailure(call: Call<List<CountriesResponse>>, t: Throwable) {
                Log.i("FAILURE ERROR", t.message.toString())
            }
        })
    }
}