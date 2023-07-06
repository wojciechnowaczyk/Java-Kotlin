package com.example.androidarchitectures.mvc

import android.util.Log
import com.example.androidarchitectures.model.CountriesResponse
import com.example.androidarchitectures.model.CountriesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesController {
    var view: MVCActivity;
    var service: CountriesService;

    constructor(view: MVCActivity){
        this.view = view;
        service = CountriesService();
        fetchCountries();
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
                        view.setValues(countryNames)
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