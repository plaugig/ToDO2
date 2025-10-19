package com.plaugig.todo2.data.database

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by George on 10/19/25.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

	@Provides
	@Singleton
	fun provides(
		@ApplicationContext context: Context
	): AppDatabase {
		return Room.databaseBuilder(
			context,
			AppDatabase::class.java, "database-name"
		).build()
	}
}