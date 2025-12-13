package com.plaugig.todo2.domain.options.use.cases

import com.plaugig.todo2.R
import com.plaugig.todo2.data.task.repository.TaskRepository
import com.plaugig.todo2.domain.options.priority.OptionsPriorityType
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItem
import com.plaugig.todo2.ui.fragments.edit.options.item.button.EditTaskButtonItem
import com.plaugig.todo2.ui.fragments.edit.options.item.selector.EditTaskSelectorItem
import com.plaugig.todo2.ui.fragments.edit.options.item.selector.item.SelectorItemData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by George on 10/25/25.
 */
class EditOptionsUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    fun getOptions(): Flow<List<EditTaskItem>> {
        return repository.getTaskById(0).map { task ->
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
                            id = 0,
                            title = "Hard",
                            color = R.color.red,
                            priority = OptionsPriorityType.Hard,
                            isSelected = task?.priority == OptionsPriorityType.Hard
                        ),
                        SelectorItemData(
                            id = 1,
                            title = "Medium",
                            color = R.color.yellow,
                            priority = OptionsPriorityType.Medium,
                            isSelected = task?.priority == OptionsPriorityType.Medium
                        ),
                        SelectorItemData(
                            id = 2,
                            title = "Ez",
                            color = R.color.purple,
                            priority = OptionsPriorityType.Ez,
                            isSelected = task?.priority == OptionsPriorityType.Ez
                        ),
                    )

                ),
            )
        }.flowOn(Dispatchers.IO).distinctUntilChanged()
    }


}