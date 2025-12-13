package com.plaugig.todo2.domain.task.use.cases

import com.plaugig.todo2.data.task.TaskData
import com.plaugig.todo2.data.task.repository.TaskRepository
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskScreenState
import com.plaugig.todo2.ui.fragments.main.task.item.TaskItemData
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
    private val repository: TaskRepository
) {

    fun getTasks(): Flow<List<TaskItemData>> {
        return repository.getAllTask().map { tasks ->
            tasks.map { task ->
                TaskItemData(
                    id = task.id,
                    name = task.name,
                    description = task.description,
                    isCompleted = task.isCompleted,
                    priority = task.priority
                )
            }
        }
    }

    fun getTaskById(taskId: Int): Flow<EditTaskScreenState?> {
        return repository.getTaskById(taskId = taskId).map { task ->
            task?.let {
                EditTaskScreenState(
                    id = task.id,
                    title = task.name,
                    description = task.description,
                    priority = task.priority

                )
            }

        }.flowOn(Dispatchers.IO).distinctUntilChanged()
    }

    suspend fun addTask(task: TaskItemData) {
        repository.addTask(
            task = TaskData(
                id = task.id,
                name = task.name,
                description = task.description,
                isCompleted = task.isCompleted,
                priority = task.priority
            )
        )
    }

    suspend fun deleteTaskById(id: Int) {
        repository.deleteTaskById(id)
    }

}