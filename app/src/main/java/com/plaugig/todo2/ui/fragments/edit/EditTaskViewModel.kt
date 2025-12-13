package com.plaugig.todo2.ui.fragments.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plaugig.todo2.domain.edit.EditInteractor
import com.plaugig.todo2.domain.options.priority.OptionsPriorityType
import com.plaugig.todo2.ui.fragments.edit.EditTaskFragment.Companion.TASK_ID
import com.plaugig.todo2.ui.fragments.edit.options.item.selector.EditTaskSelectorItem
import com.plaugig.todo2.ui.fragments.main.task.item.TaskItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
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

    private val taskId: Int = savedStateHandle.get<Int>(TASK_ID)!!
    private val _descriptionState = MutableStateFlow("")
    val descriptionState: StateFlow<String> = _descriptionState.asStateFlow()
    private val _titleState = MutableStateFlow("")
    val titleState: StateFlow<String> = _titleState.asStateFlow()
    private var priorityState = MutableStateFlow<OptionsPriorityType?>(null)

    val options = combine(
        interactor.getOptions(),
        priorityState
    ){ listItems, currentPriority ->
        listItems.map { item ->
            if (item is EditTaskSelectorItem) {
                val newButtonList = item.items.map { button ->
                    button.copy(isSelected = button.priority == currentPriority)
                }
                item.copy(items = newButtonList)
            } else{
                item
            }
        }


    } .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val task = interactor.getTaskById(taskId).firstOrNull()
            if (task != null){
                priorityState.value = task.priority
                _titleState.value = task.title
                _descriptionState.value = task.description
            }
        }
    }

    fun onDeleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.deleteTaskById(taskId)
        }
    }

    fun setTitle(title: String) = viewModelScope.launch(Dispatchers.IO) {
        _titleState.emit(title)
    }

    fun setDescription(description: String) = viewModelScope.launch(Dispatchers.IO) {
        _descriptionState.emit(description)
    }


    fun save() = viewModelScope.launch(Dispatchers.IO) {
        interactor.addTask(
            task = TaskItemData(
                description = _descriptionState.value,
                name = _titleState.value,
                isCompleted = false,
                id = taskId,
                priority = priorityState.value
            )
        )
    }

    fun updatePriority(newPriority: OptionsPriorityType?) = viewModelScope.launch(Dispatchers.IO){
        priorityState.emit(newPriority)
    }
}