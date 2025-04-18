package com.example.unimsg.utils

import androidx.room.*

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: NotificationEntity)

    @Query("SELECT * FROM notifications ORDER BY time desc")
    suspend fun getAllNotifications(): List<NotificationEntity>

    @Query("DELETE FROM notifications")
    suspend fun clearAll()
}