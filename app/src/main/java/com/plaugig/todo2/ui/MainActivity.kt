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
import com.plaugig.todo2.ui.task.TaskAdapter
import com.plaugig.todo2.ui.task.TaskItemDecoration
import com.plaugig.todo2.ui.task.item.TaskItemData

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterDay = DaysAdapter()

        binding.days.adapter = adapterDay
        binding.days.addItemDecoration(DaysItemDecoration(this))

        adapterDay.days = listOf(
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
            ),DayItemState(
                id = 5,
                nameOfDay = "Fri",
                numberOfDay = "29"
            ),DayItemState(
                id = 5,
                nameOfDay = "Fri",
                numberOfDay = "29"
            ),DayItemState(
                id = 5,
                nameOfDay = "Fri",
                numberOfDay = "29"
            ),DayItemState(
                id = 5,
                nameOfDay = "Fri",
                numberOfDay = "29"
            ),
        )

        lateinit var adapterTask : TaskAdapter
         adapterTask = TaskAdapter { task, position ->
            val updatedTask = task.copy(isDone = !task.isDone)
            adapterTask.updateTask(position, updatedTask)
        }

        binding.task.adapter = adapterTask
        binding.task.addItemDecoration(TaskItemDecoration(this))
        adapterTask.task = listOf(
            TaskItemData(
                1,
                getString(R.string.name_text) ,
                getString(R.string.text_task)
            ),
            TaskItemData(
                1,
                getString(R.string.name_text) ,
                getString(R.string.text_task)
            ),
            TaskItemData(
                1,
                getString(R.string.name_text) ,
                getString(R.string.text_task)
            ),
            TaskItemData(
                1,
                getString(R.string.name_text) ,
                getString(R.string.text_task)
            ),
            TaskItemData(
                1,
                getString(R.string.name_text) ,
                getString(R.string.text_task)
            )
        )


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}