package com.example.unimsg.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotificationDao {
    @Insert
    suspend fun insertNotification(notification: NotificationEntity)

    @Delete
    suspend fun deleteNotification(notification: NotificationEntity)

    @Query("SELECT * FROM notifications ORDER BY time DESC")
    fun getNotifications() : LiveData<MutableList<NotificationEntity>>

    @Query("DELETE FROM notifications")
    suspend fun deleteAllNotifications()
}

