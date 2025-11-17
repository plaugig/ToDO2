package com.plaugig.todo2.ui.fragments.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plaugig.todo2.domain.edit.EditInteractor
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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

    val options = interactor.getOptions()

    private val taskId: Int = savedStateHandle.get<Int>("taskId")!!

    val taskDataState: Flow<EditTaskScreenState?> = interactor.getTask(taskId)

     fun onDeliteTask(id: Int){
        viewModelScope.launch (Dispatchers.IO){
            interactor.deleteTaskById(id)
        }
    }

}