package com.example.androidarchitectures.mvc

import CountriesAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitectures.R

public class MVCActivity : AppCompatActivity() {
    private var listValues: ArrayList<String> = ArrayList()
    lateinit var countriesAdapter: CountriesAdapter
    lateinit var listRV: RecyclerView
    lateinit var controller: CountriesController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvcactivity)
        title = "MVC Activity"

        controller = CountriesController(this)

        listRV = findViewById(R.id.list);
        countriesAdapter = CountriesAdapter(countriesList = listValues)
        listRV.adapter = countriesAdapter
        countriesAdapter.notifyDataSetChanged()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setValues(values : List<String>){
        listValues.clear();
        listValues.addAll(values)
        countriesAdapter.notifyDataSetChanged()
    }
}