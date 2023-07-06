package com.example.androidarchitectures.model

import com.example.androidarchitectures.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {
    private var listCall: Call<List<CountriesResponse>>? = null;

    constructor(){
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val service : CountriesAPI = retrofit
            .create<CountriesAPI>(CountriesAPI::class.java)

        listCall = service.getCountries()
    }

    fun getCountries(): Call<List<CountriesResponse>>? {
        return listCall
    }
}