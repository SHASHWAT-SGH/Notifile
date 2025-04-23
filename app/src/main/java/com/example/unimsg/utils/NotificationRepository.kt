package com.example.unimsg.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object NotificationRepository {
    private val _notifications = MutableLiveData<MutableList<NotificationModel>>(mutableListOf())
    val notifications: LiveData<MutableList<NotificationModel>> = _notifications

    fun addNotification(notification: NotificationModel) {
        val currentList = _notifications.value ?: mutableListOf()
        currentList.add(0, notification) // Add latest notification at the top
        _notifications.postValue(currentList)
    }

    fun removeNotification(notification: NotificationModel): Int {
        val currentList = _notifications.value ?: return -1
        val index = currentList.indexOf(notification)
        if (index != -1) {
            val updatedList = currentList.toMutableList() // Create a new list to trigger LiveData update
            updatedList.removeAt(index)
            _notifications.postValue(updatedList)
        }
        return index // Return the original index
    }

    fun addNotificationAtIndex(notification: NotificationModel, index: Int) {
        val currentList = _notifications.value ?: mutableListOf()
        if (index in 0..currentList.size) {
            currentList.add(index, notification) // Restore notification at the original index
            _notifications.postValue(currentList)
        }
    }
}
