package com.plaugig.todo2.ui.fragments.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plaugig.todo2.domain.edit.EditInteractor
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskScreenState
import com.plaugig.todo2.ui.fragments.main.task.item.TaskItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
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

    private val taskId: Int = savedStateHandle.get<Int>("taskId")!!

    val taskDataState: Flow<EditTaskScreenState?> = interactor.getTaskById(taskId)

    fun onDeleteTask(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.deleteTaskById(id)
        }
    }

    private val descriptionState = MutableStateFlow("")
    private val titleState = MutableStateFlow("")

    fun setTitle(title: String) = viewModelScope.launch(Dispatchers.IO) {
        titleState.emit(title)
    }

    fun setDescription(description: String) = viewModelScope.launch(Dispatchers.IO) {
        descriptionState.emit(description)
    }

    fun save() = viewModelScope.launch(Dispatchers.IO) {
        interactor.addTask(
            task = TaskItemData(
                description = descriptionState.value,
                name = titleState.value,
                isCompleted = false,
                id = taskId
            )
        )
    }


}