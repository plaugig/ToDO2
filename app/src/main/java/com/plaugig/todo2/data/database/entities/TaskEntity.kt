package com.plaugig.todo2.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by George on 10/19/25.
 */
@Entity("tasks")
data class TaskEntity(
	@PrimaryKey val id: Int,
	@ColumnInfo(name = "name")
	val name: String,
	@ColumnInfo(name = "description")
	val description: String,
	@ColumnInfo(name = "is_completed")
	val isCompleted: Boolean,
    @ColumnInfo(name = "priority")
    val priority: Int?
)