package com.plaugig.todo2.domain

import com.plaugig.todo2.domain.tasks.TasksUseCase
import com.plaugig.todo2.ui.task.item.TaskItemData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by George on 9/14/25.
 */
class MainInteractor @Inject constructor(
	private val tasksUseCase: TasksUseCase
) {

	fun getState(): Flow<List<TaskItemData>> {
		return tasksUseCase.getTasks()
	}

	suspend fun deleteTaskById(id: Int) {
		tasksUseCase.deleteTaskById(id)
	}
}