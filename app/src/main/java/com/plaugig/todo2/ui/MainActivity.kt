package com.plaugig.todo2.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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

class MainActivity : AppCompatActivity() {

	private var _binding: ActivityMainBinding? = null
	private val binding: ActivityMainBinding get() = _binding!!

	private val viewModel: MainViewModel by viewModels()


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()

		_binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val adapterDay = DaysAdapter()
		val taskAdapter = TaskAdapter(
			listener = viewModel
		)

		binding.days.adapter = adapterDay
		binding.task.adapter = taskAdapter

		binding.days.addItemDecoration(DaysItemDecoration(this))
		binding.task.addItemDecoration(TaskItemDecoration(this))

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
			),
			DayItemState(
				id = 5,
				nameOfDay = "Fri",
				numberOfDay = "29"
			),
			DayItemState(
				id = 5,
				nameOfDay = "Fri",
				numberOfDay = "29"
			),
			DayItemState(
				id = 5,
				nameOfDay = "Fri",
				numberOfDay = "29"
			),
			DayItemState(
				id = 5,
				nameOfDay = "Fri",
				numberOfDay = "29"
			),
		)

		viewModel.tasks.observe(this) { tasks ->
			taskAdapter.task = tasks
		}


		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
	}
}