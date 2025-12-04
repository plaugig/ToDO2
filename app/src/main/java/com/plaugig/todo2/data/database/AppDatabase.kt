package com.plaugig.todo2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.plaugig.todo2.data.database.dao.TasksDao
import com.plaugig.todo2.data.database.entities.TaskEntity

/**
 * Created by George on 10/19/25.
 */
@Database(
	entities = [
		TaskEntity::class
	],
	version = 2
)
abstract class AppDatabase : RoomDatabase() {

	abstract fun tasksDao(): TasksDao

    companion object{
        val MIGRATION_1_2 = object : Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE tasks ADD COLUMN priority INTEGER DEFAULT NULL")
            }
        }
    }
}