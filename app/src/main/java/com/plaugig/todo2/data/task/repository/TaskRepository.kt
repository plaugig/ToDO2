package com.plaugig.todo2.data.task.repository

import com.plaugig.todo2.data.database.entities.TaskEntity
import com.plaugig.todo2.data.task.TaskData
import com.plaugig.todo2.data.task.data.source.LocalTaskDataSource
import com.plaugig.todo2.domain.options.priority.OptionsPriorityTypeMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by George on 10/19/25.
 */
class TaskRepository @Inject constructor(
    private val localDataSource: LocalTaskDataSource,
    private val priorityMapper: OptionsPriorityTypeMapper
) {

    fun getAllTask(): Flow<List<TaskData>> {
        return localDataSource.getTasks().map { entities ->
            entities.map { entity ->
                TaskData(
                    id = entity.id,
                    name = entity.name,
                    description = entity.description,
                    isCompleted = entity.isCompleted,
                    priority = priorityMapper.mapReverse(entity.priority)
                )
            }
        }
    }

    suspend fun addTask(task: TaskData) {
        localDataSource.addTask(
            task = TaskEntity(
                id = task.id,
                name = task.name,
                description = task.description,
                isCompleted = task.isCompleted,
                priority = priorityMapper.map(task.priority)
            )
        )
    }

    suspend fun deleteTaskById(id: Int) {
        localDataSource.deleteTaskById(
            id = id
        )
    }

    fun getTaskById(taskId: Int): Flow<TaskData?> {
        return localDataSource.getTaskById(taskId).map { entity ->
            entity?.let {
                TaskData(
                    id = entity.id,
                    name = entity.name,
                    description = entity.description,
                    isCompleted = entity.isCompleted,
                    priority = priorityMapper.mapReverse(entity.priority)
                )
            }

            }
        }
    }




