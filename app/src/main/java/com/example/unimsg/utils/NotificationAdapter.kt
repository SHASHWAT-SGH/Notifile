package com.example.unimsg.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unimsg.R

class NotificationAdapter(private var notifications: MutableList<NotificationEntity>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var appIcon = itemView.findViewById<ImageView>(R.id.img_notification_icon)
        var appName = itemView.findViewById<TextView>(R.id.txt_app_name)
        var time = itemView.findViewById<TextView>(R.id.txt_time)
        var notificationHeading = itemView.findViewById<TextView>(R.id.notification_heading)
        var notificationContent = itemView.findViewById<TextView>(R.id.notification_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]

        holder.appIcon.setImageResource(notification.appIcon)
        holder.appName.text = notification.appName
        holder.time.text = notification.time
        holder.notificationHeading.text = notification.notificationHeading
        holder.notificationContent.text = notification.notificationContent
    }

    override fun getItemCount(): Int = notifications.size

    fun removeItem(position: Int) {
        notifications.removeAt(position)
        notifyItemRemoved(position)
    }
}