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
import com.plaugig.todo2.ui.task.SwipeDeliteTask
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
		binding.taskRecV.adapter = taskAdapter

		binding.days.addItemDecoration(DaysItemDecoration(this))
		binding.taskRecV.addItemDecoration(TaskItemDecoration(this))


		viewModel.tasks.observe(this) { tasks ->
			taskAdapter.task = tasks
		}

		viewModel.days.observe(this) { days ->
			adapterDay.days = days
		}


		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}


	}
}