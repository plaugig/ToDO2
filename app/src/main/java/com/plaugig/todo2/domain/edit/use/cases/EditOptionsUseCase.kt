package com.plaugig.todo2.domain.edit.use.cases

import com.plaugig.todo2.R
import com.plaugig.todo2.data.database.dao.TasksDao
import com.plaugig.todo2.data.database.entities.TaskEntity
import com.plaugig.todo2.data.task.repository.TaskRepository
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItem
import com.plaugig.todo2.ui.fragments.edit.options.item.button.EditTaskButtonItem
import com.plaugig.todo2.ui.fragments.edit.options.item.selector.EditTaskSelectorItem
import com.plaugig.todo2.ui.fragments.edit.options.item.selector.item.SelectorItemData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by George on 10/25/25.
 */
class EditOptionsUseCase @Inject constructor(
	private val repository: TaskRepository
) {

	operator fun invoke(taskId: Int): Flow<TaskEntity> {
		return repository.getTaskById(taskId)
	}

	fun getOptions(): Flow<List<EditTaskItem>> {
		return flowOf(
			listOf(
				EditTaskButtonItem(
					title = "Reminder",
					description = "Oct 12 - 6:47 PM"
				),
				EditTaskButtonItem(
					title = "Category",
					description = "Study"
				),
				EditTaskSelectorItem(
					listOf(
						SelectorItemData(
							25,
							"Hard",
							R.color.red
						),SelectorItemData(
							42,
							"Medium",
							R.color.basic_color
						),SelectorItemData(
							23,
							"Ez",
							R.color.purple
						),
					)

				),
			)
		).flowOn(Dispatchers.IO).distinctUntilChanged()
	}
}