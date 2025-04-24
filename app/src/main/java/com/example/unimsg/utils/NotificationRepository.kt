package com.example.unimsg.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.unimsg.db.NotificationDao
import com.example.unimsg.db.NotificationEntity
import java.time.LocalDateTime

class NotificationRepository(private val dao: NotificationDao) {

    val notifications: LiveData<MutableList<NotificationEntity>> = dao.getNotifications()

    suspend fun addNotification(notification: NotificationEntity) {
        dao.insertNotification(notification)
    }

    suspend fun removeNotification(notification: NotificationEntity) {
        dao.deleteNotification(notification)
    }

    suspend fun addNotificationAtIndex(notification: NotificationEntity) {
        dao.insertNotification(notification)
    }

    suspend fun deleteAllNotification() {
        dao.deleteAllNotifications()
    }

    suspend fun deleteOldNotifications(cutoff: LocalDateTime) {
        dao.deleteOldNotifications(cutoff)
    }
}
