package com.plaugig.todo2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plaugig.todo2.data.database.dao.TasksDao
import com.plaugig.todo2.data.database.entities.TaskEntity

/**
 * Created by George on 10/19/25.
 */
@Database(
	entities = [
		TaskEntity::class
	],
	version = 1
)
abstract class AppDatabase : RoomDatabase() {

	abstract fun tasksDao(): TasksDao
}