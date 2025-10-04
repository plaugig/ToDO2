package com.plaugig.todo2.domain.tasks

import com.plaugig.todo2.data.TaskData
import com.plaugig.todo2.data.TasksDataBase
import com.plaugig.todo2.ui.task.item.TaskItemData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by George on 9/14/25.
 */
class TasksUseCase @Inject constructor(
	private val dataBase: TasksDataBase
) {

	fun getTasks(): Flow<List<TaskItemData>> {
		return dataBase.getTasks().map { tasks ->
			tasks.map { task ->
				TaskItemData(
					id = task.id,
					name = task.name,
					text = task.text,
					isDone = task.isCompleted
				)
			}
		}.flowOn(Dispatchers.IO).distinctUntilChanged()
	}

	suspend fun addTask(task: TaskItemData) {
		dataBase.addTask(
			task = TaskData(
				id = task.id,
				name = task.name,
				text = task.text,
				isCompleted = task.isDone
			)
		)
	}

	suspend fun deleteTask(id: Int) {
		dataBase.deleteTask(id)
	}
}