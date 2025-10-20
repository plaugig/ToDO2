package com.plaugig.todo2.ui.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plaugig.todo2.domain.MainInteractor
import com.plaugig.todo2.ui.days.item.DayItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
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

	private val _uiActions = MutableSharedFlow<MainFragmentUiAction>()
	val uiActions: SharedFlow<MainFragmentUiAction> get() = _uiActions


	override fun onDeleteTask(id: Int) {
		viewModelScope.launch(Dispatchers.IO) {
			interactor.deleteTaskById(id)
		}
	}

	override fun onOpenTask(id: Int) {
		_uiActions.tryEmit(
			MainFragmentUiAction.OpenTask(
				id = id
			)
		)
	}

	override fun onTaskDone(id: Int, isDone: Boolean) {
		println("FUCK: Task is done: $id")
	}
}