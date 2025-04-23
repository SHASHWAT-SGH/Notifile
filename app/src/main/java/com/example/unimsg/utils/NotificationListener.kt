package com.example.unimsg.utils

import android.app.PendingIntent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
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
            val timestamp = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                formatTime(it.postTime)
            } else {
                TODO("VERSION.SDK_INT < O")
            }

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


            // Create NotificationEntity
            val notificationEntity = NotificationModel(
                appIcon = appIcon,
                appName = appName,
                time = timestamp,
                notificationHeading = title,
                notificationContent = description
            )

            // Add to NotificationRepository
            NotificationRepository.addNotification(notificationEntity)


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
