package com.example.unimsg.utils

import androidx.lifecycle.LiveData
import com.example.unimsg.db.RecentlyClearedNotificationDao
import com.example.unimsg.db.RecentlyClearedNotificationEntity

class RecentNotificationRepository(private val dao: RecentlyClearedNotificationDao) {

    val recentNotifications: LiveData<MutableList<RecentlyClearedNotificationEntity>> = dao.getAllRecentlyCleared()

    suspend fun insertNotificaions(recent: RecentlyClearedNotificationEntity){
        dao.insert(recent)
    }

    suspend fun deleteAllNotifications() {
        dao.deleteAllNotifications()
    }
}