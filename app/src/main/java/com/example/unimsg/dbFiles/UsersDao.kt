package com.example.unimsg.dbFiles

import androidx.lifecycle.LiveData
import androidx.room.*

interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: UsersEntity)

    @Query("SELECT * FROM notifications ORDER BY timestamp DESC")
    fun getAllNotifications(): LiveData<List<UsersEntity>>

    @Query("DELETE FROM notifications WHERE id = :id")
    suspend fun deleteNotification(id: Int)

//    @Query("DELETE FROM notifications")
//    suspend fun clearAllNotifications()
}