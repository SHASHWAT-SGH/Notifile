package com.example.unimsg.utils

import android.graphics.drawable.Drawable
import java.time.LocalDateTime
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "notifications")
//data class NotificationEntity(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val appName: String,
//    val time: String, // Use String or Long, not LocalDateTime unless you have a TypeConverter
//    val notificationHeading: String,
//    val notificationContent: String
//)

//data class NotificationEntity(
//    val appIcon: Int,
//    val appName: String,
//    val time: String,
//    val notificationHeading: String,
//    val notificationContent: String
//)

//@Entity(tableName = "notifications")
//data class NotificationEntity(
//    val appIcon: Drawable?,  // Change Int to Drawable?
//    val appName: String,
//    val time: LocalDateTime,
//    val notificationHeading: String,
//    val notificationContent: String
//)

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val appIcon: ByteArray?,
    val appName: String,
    val time: String,
    val notificationHeading: String,
    val notificationContent: String
)