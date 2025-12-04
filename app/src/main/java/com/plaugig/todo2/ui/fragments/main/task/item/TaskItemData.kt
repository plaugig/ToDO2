package com.plaugig.todo2.ui.fragments.main.task.item

import com.plaugig.todo2.domain.options.priority.OptionsPriorityType

data class TaskItemData(
	val id: Int,
	val name: String,
	val description: String,
	val isCompleted: Boolean,
    val priority: OptionsPriorityType?
)
