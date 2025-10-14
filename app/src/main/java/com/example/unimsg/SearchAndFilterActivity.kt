package com.example.unimsg

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unimsg.db.NotificationDatabase
import com.example.unimsg.db.NotificationEntity
import com.example.unimsg.utils.ModalDialogFragment
import com.example.unimsg.utils.NotificationAdapter
import com.example.unimsg.utils.NotificationRepository

class SearchAndFilterActivity : AppCompatActivity() {

    private lateinit var searchInput: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificationAdapter
    private lateinit var repository: NotificationRepository
    private lateinit var filterButton: ImageButton

    private var allNotifications = listOf<NotificationEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_and_filter)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        searchInput = findViewById(R.id.edit_txt_search)
        recyclerView = findViewById(R.id.recyclerView_search)
        filterButton = findViewById(R.id.filterIcon)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NotificationAdapter(mutableListOf())
        recyclerView.adapter = adapter

        val dao = NotificationDatabase.getDatabase(this).notificationDao()
        repository = NotificationRepository(dao)

        // Observe LiveData from Room
        repository.notifications.observe(this, Observer { list ->
            allNotifications = list
            adapter.updateList(allNotifications as MutableList<NotificationEntity>)
        })

        // Listen for search input
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim().lowercase()
                val filtered = allNotifications.filter {
                    it.appName.contains(query, ignoreCase = true) ||
                            it.notificationHeading.contains(query, ignoreCase = true)
                            ||
                            it.notificationContent.contains(query, ignoreCase = true)
                }
                adapter.updateList(filtered as MutableList<NotificationEntity>)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        filterButton.setOnClickListener {
            val modal = ModalDialogFragment()
            modal.show(supportFragmentManager, "filter_modal")
        }
    }
}
