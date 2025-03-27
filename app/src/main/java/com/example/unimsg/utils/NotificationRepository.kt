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
}
