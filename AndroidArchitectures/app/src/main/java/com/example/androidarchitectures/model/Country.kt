package com.example.androidarchitectures.model

import com.google.gson.annotations.SerializedName

class Country {
    @SerializedName("name")
    lateinit var countryName: String;
}