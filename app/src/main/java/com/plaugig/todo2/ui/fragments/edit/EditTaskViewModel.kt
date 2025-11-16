package com.plaugig.todo2.ui.fragments.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plaugig.todo2.data.database.entities.TaskEntity
import com.plaugig.todo2.domain.edit.EditInteractor
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Created by George on 10/25/25.
 */
@HiltViewModel
class EditTaskViewModel @Inject constructor(
	private val interactor: EditInteractor,
	savedStateHandle: SavedStateHandle
) : ViewModel() {

		val options = interactor.getOptions()

	private val taskId: Int = savedStateHandle.get<Int>("taskId")
		?: throw IllegalArgumentException("Task ID must be provided")

	val taskDataState: StateFlow<EditTaskItemData?> =
		interactor.getTask(taskId)
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(),
				initialValue = null
			)

}