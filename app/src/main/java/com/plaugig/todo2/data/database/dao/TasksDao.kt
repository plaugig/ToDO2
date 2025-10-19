package com.plaugig.todo2.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.plaugig.todo2.data.database.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by George on 10/19/25.
 */
@Dao
interface TasksDao {

	@Query("SELECT * FROM tasks")
	fun getAll(): Flow<List<TaskEntity>>

	@Upsert
	suspend fun upsert(task: TaskEntity)

	@Query("DELETE FROM tasks WHERE id = :id")
	suspend fun deleteById(id: Int)
}