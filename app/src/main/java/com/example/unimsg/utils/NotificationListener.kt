package com.example.unimsg.utils

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.example.unimsg.dbFiles.UsersDatabase
import com.example.unimsg.dbFiles.UsersEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationListener : NotificationListenerService() {


    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        sbn?.let {
            val title = sbn.notification.extras.getString("android.title") ?: "Unknown Title"
            val content = sbn.notification.extras.getString("android.text") ?: "No Content"
            val packageName = sbn.packageName

            val notification = UsersEntity(
                title = title,
                content = content,
                packageName = packageName
            )

            CoroutineScope(Dispatchers.IO).launch {
                UsersDatabase.getDatabase(applicationContext)
                    .UsersDao()
                    .insertNotification(notification)
            }
        }
    }
}