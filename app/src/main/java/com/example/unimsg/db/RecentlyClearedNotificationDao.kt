package com.example.unimsg.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentlyClearedNotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recentlyClearedNotification: RecentlyClearedNotificationEntity)

    @Query("SELECT * FROM recently_cleared_notifications ORDER BY clearedAt DESC")
    fun getAllRecentlyCleared(): Flow<List<RecentlyClearedNotificationEntity>>
}
