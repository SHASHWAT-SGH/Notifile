package com.example.unimsg.utils

import android.graphics.drawable.Drawable
import java.time.LocalDateTime

//data class NotificationEntity(
//    val appIcon: Int,
//    val appName: String,
//    val time: String,
//    val notificationHeading: String,
//    val notificationContent: String
//)


data class NotificationEntity(
    val appIcon: Drawable?,  // Change Int to Drawable?
    val appName: String,
    val time: LocalDateTime,
    val notificationHeading: String,
    val notificationContent: String
)