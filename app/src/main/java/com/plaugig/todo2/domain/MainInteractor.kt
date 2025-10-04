package com.plaugig.todo2.domain

import com.plaugig.todo2.domain.tasks.TasksUseCase
import javax.inject.Inject

/**
 * Created by George on 9/14/25.
 */
class MainInteractor @Inject constructor(
	private val tasksUseCase: TasksUseCase
) {

	private fun deleteTask() {

	}
}