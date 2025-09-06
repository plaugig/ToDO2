package com.plaugig.todo2.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.plaugig.todo2.R
import com.plaugig.todo2.databinding.ActivityMainBinding
import com.plaugig.todo2.ui.days.DaysAdapter
import com.plaugig.todo2.ui.days.DaysItemDecoration
import com.plaugig.todo2.ui.days.item.DayItemState

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = DaysAdapter()

        binding.days.adapter = adapter
        binding.days.addItemDecoration(DaysItemDecoration(this))

        adapter.days = listOf(
            DayItemState(
                id = 1,
                nameOfDay = "Mon",
                numberOfDay = "25"
            ),
            DayItemState(
                id = 2,
                nameOfDay = "Tue",
                numberOfDay = "26"
            ),
            DayItemState(
                id = 3,
                nameOfDay = "Wed",
                numberOfDay = "27"
            ),
            DayItemState(
                id = 4,
                nameOfDay = "Thu",
                numberOfDay = "28"
            ),
            DayItemState(
                id = 5,
                nameOfDay = "Fri",
                numberOfDay = "29"
            ),
        )


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}