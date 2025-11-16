package com.plaugig.todo2.ui.fragments.edit

import androidx.lifecycle.ViewModel
import com.plaugig.todo2.data.database.entities.TaskEntity
import com.plaugig.todo2.domain.edit.EditInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

/**
 * Created by George on 10/25/25.
 */
@HiltViewModel
class EditTaskViewModel @Inject constructor(
	private val interactor: EditInteractor
) : ViewModel() {

		val options = interactor.getOptions()

	private val _taskId = MutableStateFlow<Int?>(null)

	val taskFlow: Flow<TaskEntity> = _taskId
		.filterNotNull()
		.flatMapLatest { id -> interactor.getTask(id) }

	fun loadTask(id: Int){
		_taskId.value = id
	}

}