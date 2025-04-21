package com.example.unimsg.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import java.io.ByteArrayOutputStream

// Made this util function for fixing com.appname error [Only found in my device works perfectly on other devices]

fun getAppIconByteArray(context: Context, packageName: String): ByteArray? {
    return try{
        val drawable = context.packageManager.getApplicationIcon(packageName)
        val bitmap = (drawable as BitmapDrawable).bitmap

        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.toByteArray()
    } catch (e: Exception){
        null
    }
}