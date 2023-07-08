package com.example.trelloclone

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    private lateinit var tv_appname: TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        tv_appname = findViewById<TextView>(R.id.tv_app_name);
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val typeface : Typeface = Typeface.createFromAsset(assets, "carbon bl.ttf")
        tv_appname.typeface = typeface;
    }
}