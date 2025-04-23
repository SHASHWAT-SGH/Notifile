package com.example.unimsg.utils

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import java.io.ByteArrayOutputStream

fun drawableToByteArray(drawable: Drawable?): ByteArray? {
    val bitmap = (drawable as? BitmapDrawable)?.bitmap ?: return null
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}