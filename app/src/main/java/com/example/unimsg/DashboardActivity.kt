package com.example.unimsg

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unimsg.utils.NotificationAdapter
import com.example.unimsg.utils.NotificationEntity
import com.example.unimsg.utils.NotificationRepository
import com.example.unimsg.utils.checkNotificationPermission
import com.example.unimsg.utils.getStatusBarHeight
import com.google.android.material.snackbar.Snackbar

class DashboardActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificationAdapter
    private var notificationList = mutableListOf<NotificationEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        checkNotificationPermission(this)

        val mainLayout = findViewById<View>(R.id.main_dashboard)
        mainLayout.setPadding(0, getStatusBarHeight(this)+40, 0, 0)

        adapter = NotificationAdapter(notificationList)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

//        notificationList.add(
//            NotificationEntity(
//                R.drawable.ic_whatsapp,
//                "WhatsApp",
//                "4:13 pm",
//                "Meghana",
//                "Reacted ❤️ \"Oookkk\""
//            )
//        )

        NotificationRepository.notifications.observe(this, Observer { newList ->
            notificationList.clear()
            notificationList.addAll(newList)  // Update notificationList
            adapter.notifyDataSetChanged()    // Notify RecyclerView to refresh
        })


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
            val deleteIcon = ContextCompat.getDrawable(context, R.drawable.baseline_delete_outline_24)
//            val backgroundColorDelete = Color.parseColor("#F44336") // Red for delete

            val paint = Paint()

            // Swipe left (Delete)
            if (dX < -180) {
                val backgroundRect = RectF(
                    itemView.right + dX, itemView.top.toFloat(),
                    itemView.right.toFloat(), itemView.bottom.toFloat()
                )

                // Draw background
//                paint.color = backgroundColorDelete
//                c.drawRect(backgroundRect, paint)

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