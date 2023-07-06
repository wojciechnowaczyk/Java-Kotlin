package com.example.androidarchitectures.mvp

import CountriesAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitectures.R
import com.example.androidarchitectures.mvc.CountriesController
import com.example.androidarchitectures.mvc.MVCActivity

class MVPActivity : AppCompatActivity(), CountriesPresenter.IView {
    private var listValues: ArrayList<String> = ArrayList()
    lateinit var countriesAdapter: CountriesAdapter
    lateinit var listRV: RecyclerView
    lateinit var presenter: CountriesPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvpactivity)
        title = "MVP Activity"

        presenter = CountriesPresenter(this)

        listRV = findViewById(R.id.list);
        countriesAdapter = CountriesAdapter(countriesList = listValues)
        listRV.adapter = countriesAdapter
        countriesAdapter.notifyDataSetChanged()
    }

    override fun setValues(countries: List<String>) {
        listValues.clear();
        listValues.addAll(countries)
        countriesAdapter.notifyDataSetChanged()
    }
}