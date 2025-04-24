package com.example.unimsg

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class CustomSplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        enableEdgeToEdge()

        val logo = findViewById<ImageView>(R.id.logo_image)
        val appName = findViewById<TextView>(R.id.app_name)
        val loadingText = findViewById<TextView>(R.id.loading_text)

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)

        logo.startAnimation(fadeIn)

        appName.startAnimation(slideUp)
        loadingText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))


        // Optional: Start loading DB, prefs, or initial data here

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000) // Show for 2 seconds
    }
}
