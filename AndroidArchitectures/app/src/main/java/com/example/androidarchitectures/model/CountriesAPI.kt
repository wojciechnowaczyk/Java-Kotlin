package com.example.androidarchitectures.model

import retrofit2.http.GET
import retrofit2.Call

interface CountriesAPI {
    @GET("all")
    fun getCountries() : Call<CountriesResponse>

}