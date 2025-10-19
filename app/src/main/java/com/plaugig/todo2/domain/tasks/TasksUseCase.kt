package com.plaugig.todo2.domain.tasks

import com.plaugig.todo2.data.task.TaskData
import com.plaugig.todo2.data.task.repository.TaskRepository
import com.plaugig.todo2.ui.task.item.TaskItemData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by George on 9/14/25.
 */
class TasksUseCase @Inject constructor(
	private val repository: TaskRepository
) {

	fun getTasks(): Flow<List<TaskItemData>> {
		return repository.getTasks().map { tasks ->
			tasks.map { task ->
				TaskItemData(
					id = task.id,
					name = task.name,
					description = task.description,
					isCompleted = task.isCompleted
				)
			}
		}
	}

	suspend fun addTask(task: TaskItemData) {
		repository.addTask(
			task = TaskData(
				id = task.id,
				name = task.name,
				description = task.description,
				isCompleted = task.isCompleted
			)
		)
	}

	suspend fun deleteTaskById(id: Int) {
		repository.deleteTaskById(id)
	}
}