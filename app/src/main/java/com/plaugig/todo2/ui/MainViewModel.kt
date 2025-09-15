package com.plaugig.todo2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plaugig.todo2.R
import com.plaugig.todo2.ui.days.item.DayItemState
import com.plaugig.todo2.ui.task.item.TaskItemData

/**
 * Created by George on 9/14/25.
 */
class MainViewModel : ViewModel(), MainEventListener {

	private val _tasks = MutableLiveData<List<TaskItemData>>(
		listOf(
			TaskItemData(
				id = 0,
				nameRes = R.string.name_text,
				textRes = R.string.text_task,
				isDone = false
			),
			TaskItemData(
				id = 1,
				nameRes = R.string.name_text,
				textRes = R.string.text_task,
				isDone = false
			),
			TaskItemData(
				id = 2,
				nameRes = R.string.name_text,
				textRes = R.string.text_task,
				isDone = false
			),
			TaskItemData(
				id = 3,
				nameRes = R.string.name_text,
				textRes = R.string.text_task,
				isDone = false
			)
		)
	)
	val tasks: LiveData<List<TaskItemData>> get() = _tasks

	private val _days = MutableLiveData<List<DayItemState>>(
		 listOf(
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
	)
	val days : LiveData<List<DayItemState>> get() = _days




	override fun onOpenTask(id: Int) {
		println("FUCK: Open task: $id")
	}

	override fun onDeleteTask(id: Int) {

	}

	override fun onTaskDone(id: Int, isDone: Boolean) {
		val tasks = tasks.value?.toMutableList()

		tasks?.let {
			val currentTask = tasks.find { it.id == id }

			currentTask?.let { task ->
				tasks.remove(task)
				tasks.add(
					task.copy(
						isDone = !isDone
					)
				)
			}
		}

		_tasks.postValue(tasks)
	}
}