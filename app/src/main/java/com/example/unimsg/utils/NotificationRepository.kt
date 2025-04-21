package com.example.unimsg.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object NotificationRepository {
    private val _notifications = MutableLiveData<MutableList<NotificationEntity>>(mutableListOf())
    val notifications: LiveData<MutableList<NotificationEntity>> = _notifications

    fun addNotification(notification: NotificationEntity) {
        val currentList = _notifications.value ?: mutableListOf()
        currentList.add(0, notification) // Add latest notification at the top
        _notifications.postValue(currentList)

    }

    suspend fun removeNotification(notification: NotificationEntity, dao: NotificationDao): Int {
//        val currentList = _notifications.value ?: return -1
//        val index = currentList.indexOf(notification)
//        if (index != -1) {
//            val updatedList = currentList.toMutableList() // Create a new list to trigger LiveData update
//            updatedList.removeAt(index)
//            _notifications.postValue(updatedList)
//        }
//        return index // Return the original index
        val currentList = _notifications.value ?: return -1
        val index = currentList.indexOf(notification)
        if(index != -1){
            val updatedList = currentList.toMutableList()
            updatedList.removeAt(index)
            _notifications.postValue(updatedList)
            dao.deleteNotificationById(notification.id)
        }
        return index
    }

    suspend fun addNotificationAtIndex(notification: NotificationEntity, index: Int, dao: NotificationDao) {
//        val currentList = _notifications.value ?: mutableListOf()
//        if (index in 0..currentList.size) {
//            currentList.add(index, notification) // Restore notification at the original index
//            _notifications.postValue(currentList)
//        }

        val currentList = _notifications.value ?: mutableListOf()
        if(index in 0..currentList.size){
            dao.insert(notification)
            currentList.add(index, notification)
            _notifications.postValue(currentList)
        }

    }
}
