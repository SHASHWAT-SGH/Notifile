package com.example.unimsg

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.unimsg.db.NotificationDatabase
import com.example.unimsg.db.NotificationEntity
import com.example.unimsg.utils.FirstLaunchHelper
import com.example.unimsg.utils.NotificationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    lateinit var repository: NotificationRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val dao = NotificationDatabase.getDatabase(this).notificationDao()
        repository = NotificationRepository(dao)

        lifecycleScope.launch(Dispatchers.IO) {
            val cutoff = LocalDateTime.now().minusHours(24)
//            val db = NotificationDatabase.getDatabase(applicationContext)
//            db.notificationDao().deleteOldNotifications(cutoff)
            repository.deleteOldNotifications(cutoff)
        }

        if (FirstLaunchHelper.isFirstLaunch(this)) {
            FirstLaunchHelper.setFirstLaunchFlag(this)
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent);
            finish()
        }else{
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent);
            finish()
        }
    }
}
