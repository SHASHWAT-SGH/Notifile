package com.example.unimsg

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class DashboardActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificationAdapter
    private var notificationList = mutableListOf<NotificationEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        notificationList.add(NotificationEntity("WhatsApp", "New message from John", "2 min ago"))
        notificationList.add(NotificationEntity("Instagram", "New follower request", "5 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))
        notificationList.add(NotificationEntity("Gmail", "Your order has been shipped", "10 min ago"))

        adapter = NotificationAdapter(notificationList)
        recyclerView.adapter = adapter

        // Attach swipe gestures
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)

    }
    private val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false // We are not handling move actions
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val deletedNotification = notificationList[position] // Store for UNDO

//            if (direction == ItemTouchHelper.RIGHT) {
//                // Archive action
//                notificationList.removeAt(position)
//                adapter.notifyItemRemoved(position)
//                Snackbar.make(recyclerView, "Notification archived", Snackbar.LENGTH_LONG)
//                    .setAction("UNDO") {
//                        notificationList.add(position, deletedNotification)
//                        adapter.notifyItemInserted(position)
//                    }.show()

//            } else
                if (direction == ItemTouchHelper.LEFT) {
                // Delete action
                notificationList.removeAt(position)
                adapter.notifyItemRemoved(position)
                Snackbar.make(recyclerView, "Notification deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
                        notificationList.add(position, deletedNotification)
                        adapter.notifyItemInserted(position)
                    }.show()
            }
        }


        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            val itemView = viewHolder.itemView
            val context = recyclerView.context
            val deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete)
            val backgroundColorDelete = Color.parseColor("#F44336") // Red for delete

            val paint = Paint()

            // Swipe left (Delete)
            if (dX < 0) {
                val backgroundRect = RectF(
                    itemView.right + dX, itemView.top.toFloat(),
                    itemView.right.toFloat(), itemView.bottom.toFloat()
                )

                // Draw background
                paint.color = backgroundColorDelete
                c.drawRect(backgroundRect, paint)

                // Draw delete icon
                deleteIcon?.let {
                    val iconSize = it.intrinsicHeight
                    val iconMargin = (itemView.height - iconSize) / 2

                    val iconLeft = itemView.right - iconSize - iconMargin
                    val iconTop = itemView.top + iconMargin
                    val iconRight = itemView.right - iconMargin
                    val iconBottom = iconTop + iconSize

                    it.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                    it.draw(c)
                }
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }
}