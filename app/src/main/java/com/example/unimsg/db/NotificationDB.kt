package com.example.unimsg.db


//import androidx.room.Database
//import androidx.room.RoomDatabase
//import com.example.unimsg.utils.NotificationEntity
//
//@Database(
//    entities = [NotificationEntity::class],
//    version = 1
//)
//
//abstract class NotificationDB: RoomDatabase() {
//
//    abstract val dao : NotificationDao
//}


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}