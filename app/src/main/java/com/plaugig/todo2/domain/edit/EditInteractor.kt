package com.plaugig.todo2.domain.edit

import com.plaugig.todo2.data.database.entities.TaskEntity
import com.plaugig.todo2.domain.edit.use.cases.EditOptionsUseCase
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by George on 10/25/25.
 */
class EditInteractor @Inject constructor(
	private val editOptionsUseCase: EditOptionsUseCase
) {

	fun getOptions(): Flow<List<EditTaskItem>> {
		return editOptionsUseCase.getOptions()
	}

	fun getTask(taskId: Int): Flow<TaskEntity> {
		return editOptionsUseCase(taskId)
	}
}