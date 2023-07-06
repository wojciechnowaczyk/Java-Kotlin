package com.example.androidarchitectures.mvvm

import CountriesAdapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitectures.R

class MVVMActivity : AppCompatActivity() {
    private var listValues: ArrayList<String> = ArrayList()
    lateinit var countriesAdapter: CountriesAdapter
    lateinit var listRV: RecyclerView
    lateinit var viewModel: CountriesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvcactivity)
        title = "MVVM Activity"

        viewModel = androidx.lifecycle.ViewModelProvider(this)[CountriesViewModel::class.java]

        listRV = findViewById(R.id.list);
        countriesAdapter = CountriesAdapter(countriesList = listValues)
        listRV.adapter = countriesAdapter
        countriesAdapter.notifyDataSetChanged()

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.getCountries().observe(this) { countries ->
            run {
                if (countries != null) {
                    listValues.clear()
                    listValues.addAll(countries)
                    listRV.visibility = View.VISIBLE
                    countriesAdapter.notifyDataSetChanged()
                }else{
                    listRV.visibility = View.GONE
                }
            }
        }
    }
}