package com.example.androidarchitectures.mvc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.androidarchitectures.R

public class MVCActivity : AppCompatActivity() {
    private var listValues: ArrayList<String> = ArrayList();
    lateinit var adapter: ListAdapter;
    lateinit var list: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvcactivity)
        title = "MVC Activity"

        list = findViewById(R.id.list);
        adapter = ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listValues)

        list.adapter = adapter
        list.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            Toast.makeText(this, "You clicked item list", Toast.LENGTH_LONG)
        }
        var testList : ArrayList<String> = ArrayList();
        testList.add("Poland");
        testList.add("Germany")
        if (testList != null) {
            setValues(testList)
        }
    }

    fun setValues(values : List<String>){
        listValues.clear();
        listValues.addAll(values)
    }
}