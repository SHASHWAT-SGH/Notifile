package com.example.unimsg.db

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.unimsg.utils.drawableToByteArray
import java.time.LocalDateTime

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val appName: String,
    val time: LocalDateTime,
    val notificationHeading: String,
    val notificationContent: String,
    val appIcon: ByteArray? // Store Drawable as ByteArray
)


//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//
//@Entity
//data class User(
//    @PrimaryKey val uid: Int,
//    @ColumnInfo(name = "first_name") val firstName: String?,
//    @ColumnInfo(name = "last_name") val lastName: String?
//)