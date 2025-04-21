package com.example.unimsg.utils

import android.app.PendingIntent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NotificationListener : NotificationListenerService() {

//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onNotificationPosted(sbn: StatusBarNotification?) {
//        sbn?.let {
//            val packageName = it.packageName // App package name
//            val notification = it.notification
//            val extras = notification.extras
//
//            // Get App Name (using package manager)
//            val appName = try {
//                val appInfo = packageManager.getApplicationInfo(packageName, 0)
//                packageManager.getApplicationLabel(appInfo).toString()
//            } catch (e: Exception) {
//                packageName
//            }
//
//            // Get Notification Title & Description
//            val title = extras.getString("android.title") ?: "No Title"
//            val description = extras.getString("android.text") ?: "No Description"
//
//            // Get Notification Time & Date
//            val timestamp = formatTime(it.postTime)
//
//            // Get App Icon
//            val appIcon: Drawable? = try {
//                packageManager.getApplicationIcon(packageName)
//
//            } catch (e: Exception) {
//                null
//            }
//            val appIconBitmap = (appIcon as? BitmapDrawable)?.bitmap // To store in room database and display it.
//
//
//            // Get PendingIntent for redirecting to the app
//            val pendingIntent: PendingIntent? = notification.contentIntent
////            pendingIntent?.send()
//
//
//            // Create NotificationEntity
//            val notificationEntity = NotificationEntity(
//                appIcon = appIconBitmap,
//                appName = appName,
//                time = timestamp,
//                notificationHeading = title,
//                notificationContent = description
//            )
//
//            // Add to NotificationRepository
//            NotificationRepository.addNotification(notificationEntity)
//
//
//            // Log the details
//            Log.d("NotificationListener", "App Name: $appName")
//            Log.d("NotificationListener", "Title: $title")
//            Log.d("NotificationListener", "Description: $description")
//            Log.d("NotificationListener", "Time: $timestamp")
//            Log.d("NotificationListener", "Redirect Intent: ${pendingIntent != null}")
//            Log.d("NotificationListener", "Redirect: $pendingIntent")
//
//
//            // If you want to store the data, you can save it in Room Database here.
//        }
//    }

    private val database: NotificationDatabase by lazy { NotificationDatabase.getDatabase(applicationContext) }
    private val scope = CoroutineScope(Dispatchers.IO)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        Log.d("NotificationListener", "Notification received")
        sbn?.let {
            val packageName = it.packageName
            val notification = it.notification
            val extras = notification.extras
            Log.d("Check String", "Notification Triggered")

            val appName = try {
                val appInfo = packageManager.getApplicationInfo(packageName, 0)
                packageManager.getApplicationLabel(appInfo).toString()
            } catch (e: Exception) {
                packageName
            }

            val title = extras.getString("android.title") ?: "No Title"
            val description = extras.getString("android.text") ?: "No Description"

            val timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

            val appIconByteArray: ByteArray? = try {
                val appIcon = packageManager.getApplicationIcon(packageName)
                drawableToByteArray(appIcon)
            } catch (e: Exception) {
                null
            }

            val notificationEntity = NotificationEntity(
                appIcon = appIconByteArray,
                appName = appName,
                time = timestamp,
                notificationHeading = title,
                notificationContent = description
            )

            scope.launch {
                Log.d("NotificationListener", "Inserting notification into database...")
                database.notificationDao().insert(notificationEntity)
                Log.d("NotificationListener", "Notification posted into Database")
            }
            NotificationRepository.addNotification(notificationEntity)

            Log.d("NotificationListener", "App Name: $appName")
            Log.d("NotificationListener", "Title: $title")
            Log.d("NotificationListener", "Description: $description")
            Log.d("NotificationListener", "Time: $timestamp")
        }
    }

    private fun drawableToByteArray(appIcon: Drawable): ByteArray? {
        val bitmap = if (appIcon is BitmapDrawable) {
            appIcon.bitmap
        } else {
            val bitmap = Bitmap.createBitmap(appIcon.intrinsicWidth, appIcon.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            appIcon.setBounds(0, 0, canvas.width, canvas.height)
            appIcon.draw(canvas)
            bitmap
        }
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        sbn?.let {
            Log.d("NotificationListener", "Notification removed from ${it.packageName}")
        }
    }
}
