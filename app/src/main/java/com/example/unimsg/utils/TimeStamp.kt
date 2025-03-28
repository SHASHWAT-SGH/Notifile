package com.example.unimsg.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun formatTime(timestamp: Long): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
}
