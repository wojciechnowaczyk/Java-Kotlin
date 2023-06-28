package com.example.androidarchitectures.mvp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidarchitectures.R
import com.example.androidarchitectures.mvc.MVCActivity

class MVPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvpactivity)
        title = "MVP Activity"
    }
}