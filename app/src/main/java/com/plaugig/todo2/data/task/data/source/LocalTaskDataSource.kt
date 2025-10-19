package com.plaugig.todo2.data.task.data.source

import com.plaugig.todo2.data.database.AppDatabase
import com.plaugig.todo2.data.database.entities.TaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by George on 10/19/25.
 */
class LocalTaskDataSource @Inject constructor(
	private val database: AppDatabase
) {

	fun getTasks(): Flow<List<TaskEntity>> {
		return database.tasksDao().getAll()
			.distinctUntilChanged()
			.flowOn(Dispatchers.IO)
	}

	suspend fun addTask(task: TaskEntity) {
		database.tasksDao().upsert(
			task = task
		)
	}

	suspend fun deleteTaskById(id: Int) {
		database.tasksDao().deleteById(
			id = id
		)
	}
}