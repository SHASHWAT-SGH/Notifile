package com.example.unimsg.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "notifications")
class NotificationEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var appName: String = ""

    var time: LocalDateTime = LocalDateTime.of(
        2020,
        2,
        15,
        16,
        48,
        59,
        999_999_999
    )
    var notificationHeading: String = ""
    var notificationContent: String = ""
    var appIcon: ByteArray = byteArrayOf()
}


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