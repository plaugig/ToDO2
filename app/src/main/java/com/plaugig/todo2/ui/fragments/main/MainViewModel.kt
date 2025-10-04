package com.plaugig.todo2.ui.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plaugig.todo2.domain.MainInteractor
import com.plaugig.todo2.ui.days.item.DayItemState
import com.plaugig.todo2.ui.task.item.TaskItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by George on 9/14/25.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
	private val interactor: MainInteractor
) : ViewModel(), MainEventListener {

	val state = interactor.getState()

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
	val days: LiveData<List<DayItemState>> get() = _days


	fun addTask() {
		viewModelScope.launch(Dispatchers.IO) {
			interactor.addTask(
				TaskItemData(
					id = (0..10000000).random(),
					name = "Task",
					text = Math.random().toString(),
					isDone = false
				)
			)
		}
	}

	override fun onDeleteTask(id: Int) {
		viewModelScope.launch(Dispatchers.IO) {
			interactor.deleteTask(id)
		}
	}

	override fun onOpenTask(id: Int) {
		println("FUCK: Open task: $id")
	}

	override fun onTaskDone(id: Int, isDone: Boolean) {
//        val currentList = tasks.value!!.toMutableList()
//
//        currentList?.let {
//            val currentTask = currentList.find { it.id == id }
//
//            currentTask?.let { task ->
//                currentList.remove(task)
//                currentList.add(
//                    task.copy(
//                        isDone = !isDone
//                    )
//                )
//            }
//        }
//
//        _tasks.postValue(currentList)
	}
}