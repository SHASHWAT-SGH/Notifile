package com.example.unimsg.dbFiles

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class UsersEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val packageName: String // To identify app source
)
