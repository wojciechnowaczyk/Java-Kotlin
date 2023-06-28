package com.example.androidarchitectures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.androidarchitectures.mvc.MVCActivity
import com.example.androidarchitectures.mvp.MVPActivity
import com.example.androidarchitectures.mvvm.MVVMActivity

class ArchitecturesActivity : AppCompatActivity() {
    lateinit var mvc_btn: Button
    lateinit var mvp_btn: Button
    lateinit var mvvm_btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.architecture_activity)
        mvc_btn = findViewById(R.id.buttonMVC)
        mvp_btn = findViewById(R.id.buttonMVP)
        mvvm_btn = findViewById(R.id.buttonMVVM)
        mvc_btn.setOnClickListener{onMVCClick()}
        mvp_btn.setOnClickListener{onMVPClick()}
        mvvm_btn.setOnClickListener{onMVVMClick()}
    }

    private fun onMVCClick(){
        val intent = Intent(this, MVCActivity::class.java)
        startActivity(intent)
    }

    private fun onMVPClick() {
        val intent = Intent(this, MVPActivity::class.java)
        startActivity(intent)
    }

    private fun onMVVMClick(){
        val intent = Intent(this, MVVMActivity::class.java)
        startActivity(intent)
    }
}