package com.plaugig.todo2.ui.bottom.sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plaugig.todo2.domain.tasks.TasksUseCase
import com.plaugig.todo2.ui.task.item.TaskItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskBottomSheetViewModel @Inject constructor(
    private val tasksUseCase: TasksUseCase
) : ViewModel() {

    private val titleState = MutableStateFlow("")
    private val descriptionState = MutableStateFlow("")

    fun setTitle(title: String) = viewModelScope.launch(Dispatchers.IO) {
        titleState.emit(title)
    }

    fun setDescription(description: String) = viewModelScope.launch(Dispatchers.IO) {
        descriptionState.emit(description)

    }

    fun save() = viewModelScope.launch(Dispatchers.IO) {
        tasksUseCase.addTask(
            task = TaskItemData(
                id = (0..10000000).random(),
                name = titleState.value,
                text = descriptionState.value,
                isDone = false
            )
        )
    }
}