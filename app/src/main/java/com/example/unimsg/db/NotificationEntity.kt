package com.example.unimsg.db

//import android.graphics.drawable.Drawable
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import java.time.LocalDateTime
//
//@Entity(tableName = "notifications")
//class NotificationEntity {
//    @PrimaryKey(autoGenerate = true)
//    var id: Int = 0
//
//    val appName: String = ""
//    @RequiresApi(Build.VERSION_CODES.O)
//    val time: LocalDateTime = LocalDateTime.of(
//        2020,
//        2,
//        15,
//        16,
//        48,
//        59,
//        999_999_999
//    )
//    val notificationHeading: String = ""
//    val notificationContent: String = ""
//    val appIcon: ByteArray = byteArrayOf()
//}


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)