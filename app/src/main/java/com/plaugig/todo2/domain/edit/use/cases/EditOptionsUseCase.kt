package com.plaugig.todo2.domain.edit.use.cases

import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItem
import com.plaugig.todo2.ui.fragments.edit.options.item.button.EditTaskButtonItem
import com.plaugig.todo2.ui.fragments.edit.options.item.selector.EditTaskSelectorItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by George on 10/25/25.
 */
class EditOptionsUseCase @Inject constructor() {

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
				)
			)
		).flowOn(Dispatchers.IO).distinctUntilChanged()
	}
}