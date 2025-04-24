package com.example.unimsg.utils

import android.app.PendingIntent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import android.widget.Switch
import com.example.unimsg.R
import com.example.unimsg.db.NotificationDatabase
import com.example.unimsg.db.NotificationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        val notificationSwitch = NotificationSwitchStateHelper.getSwitchState(this)
        if(!notificationSwitch){
            return
        }

        sbn?.let {
            val packageName = it.packageName // App package name
            val notification = it.notification
            val extras = notification.extras

            // Get App Name (using package manager)
            val appName = try {
                val appInfo = packageManager.getApplicationInfo(packageName, 0)
                packageManager.getApplicationLabel(appInfo).toString()
            } catch (e: Exception) {
                packageName
            }

            // Get Notification Title & Description
            val title = extras.getString("android.title") ?: "No Title"
            val description = extras.getString("android.text") ?: "No Description"

            // Get Notification Time & Date
            val timestamp = formatTime(it.postTime)

            // Get App Icon
            val appIcon: Drawable? = try {
                packageManager.getApplicationIcon(packageName)

            } catch (e: Exception) {
                null
            }
            val appIconBitmap = (appIcon as? BitmapDrawable)?.bitmap // To store in room database and display it.


            // Get PendingIntent for redirecting to the app
            val pendingIntent: PendingIntent? = notification.contentIntent
//            pendingIntent?.send()


            // Add to NotificationRepository
            CoroutineScope(Dispatchers.IO).launch {
                val database = NotificationDatabase.getDatabase(applicationContext)
                val dao = database.notificationDao()

                val entity = NotificationEntity(
                    appName = appName,
                    notificationHeading = title,
                    notificationContent = description,
                    time = timestamp,
                    appIcon = drawableToByteArray(appIcon)
                )

                dao.insertNotification(entity)
            }


            // Log the details
            Log.d("NotificationListener", "App Name: $appName")
            Log.d("NotificationListener", "Title: $title")
            Log.d("NotificationListener", "Description: $description")
            Log.d("NotificationListener", "Time: $timestamp")
            Log.d("NotificationListener", "Redirect Intent: ${pendingIntent != null}")
            Log.d("NotificationListener", "Redirect: $pendingIntent")


            // If you want to store the data, you can save it in Room Database here.
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        sbn?.let {
            Log.d("NotificationListener", "Notification removed from ${it.packageName}")
        }
    }
}
