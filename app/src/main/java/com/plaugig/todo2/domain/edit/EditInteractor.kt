package com.plaugig.todo2.domain.edit

import com.plaugig.todo2.domain.options.use.cases.EditOptionsUseCase
import com.plaugig.todo2.domain.task.use.cases.TasksUseCase
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItem
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskScreenState
import com.plaugig.todo2.ui.fragments.main.task.item.TaskItemData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by George on 10/25/25.
 */
class EditInteractor @Inject constructor(
	private val editOptionsUseCase: EditOptionsUseCase,
	private val tasksUseCase: TasksUseCase
) {

	fun getOptions(): Flow<List<EditTaskItem>> {
		return editOptionsUseCase.getOptions()
	}

	fun getTaskById(taskId: Int): Flow<EditTaskScreenState?> {
		return tasksUseCase.getTaskById(taskId)
	}

	suspend fun deleteTaskById(id: Int){
		tasksUseCase.deleteTaskById(id)
	}

	suspend fun addTask(task: TaskItemData){
		tasksUseCase.addTask(task)
	}

}