package com.example.androidarchitectures.mvvc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidarchitectures.R
import com.example.androidarchitectures.mvc.MVCActivity

class MVVCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvcactivity)
    }

    public fun getIntent(context: Context){
        val intent = Intent(this, MVVCActivity::class.java)
        return startActivity(intent)
    }
}