package com.example.unimsg

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.unimsg.db.NotificationDatabase
import com.example.unimsg.db.NotificationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        ---------




//        ------------

//        start onboarding screen
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent);
        finish()
    }
}
