package com.plaugig.todo2.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by George on 10/4/25.
 */
@Singleton
class TasksDataBase @Inject constructor() {

	private val tasksState = MutableStateFlow<List<TaskData>>(emptyList())


	fun getTasks(): Flow<List<TaskData>> {
		return tasksState
	}

	suspend fun addTask(task: TaskData) {
		val currentTasks = tasksState.first().toMutableList()
		val shouldCreate = currentTasks.find { it.id == task.id } == null

		if (shouldCreate) {
			currentTasks.add(task)
			tasksState.emit(currentTasks)
		}
	}

	suspend fun deleteTask(id: Int) {
		val currentTasks = tasksState.first().toMutableList()

		currentTasks.removeIf { it.id == id }

		tasksState.emit(currentTasks)
	}
}