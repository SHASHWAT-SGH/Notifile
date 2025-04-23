package com.example.unimsg.utils

import android.graphics.BitmapFactory
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.unimsg.R
import com.example.unimsg.db.NotificationEntity

class NotificationAdapter(private var notifications: MutableList<NotificationEntity>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var appIcon: ImageView = itemView.findViewById(R.id.img_notification_icon)
        var appName: TextView = itemView.findViewById(R.id.txt_app_name)
        var time: TextView = itemView.findViewById(R.id.txt_time)
        var notificationHeading: TextView = itemView.findViewById(R.id.notification_heading)
        var notificationContent: TextView = itemView.findViewById(R.id.notification_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }


    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]

        // Set app icon safely
        val byteArrayAppIcon = notification.appIcon  // this is ByteArray? from your Room entity

        if (byteArrayAppIcon != null) {
            val bitmap = BitmapFactory.decodeByteArray(byteArrayAppIcon, 0, byteArrayAppIcon.size)
            holder.appIcon.setImageBitmap(bitmap)
        } else {
            holder.appIcon.setImageResource(R.drawable.ic_archive) // fallback icon
        }

        holder.appName.text = notification.appName
        holder.time.text = buildString {
            append(notification.time.hour.toString())
            append(":")
            append(notification.time.minute.toString())
        }
        holder.notificationHeading.text = notification.notificationHeading
        holder.notificationContent.text = notification.notificationContent
    }

    override fun getItemCount(): Int = notifications.size

    // Function to update the list dynamically
    fun updateList(newList: MutableList<NotificationEntity>) {
        notifications.clear()
        notifications.addAll(newList)
        notifyDataSetChanged()  // Notify RecyclerView of data change
    }

    fun removeItem(position: Int) {
        notifications.removeAt(position)
        notifyItemRemoved(position)
    }
}
