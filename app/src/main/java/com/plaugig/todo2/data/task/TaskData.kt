package com.plaugig.todo2.data.task

import com.plaugig.todo2.domain.options.priority.OptionsPriorityType

/**
 * Created by George on 10/4/25.
 */
data class TaskData(
	val id: Int,
	val name: String,
	val description: String,
	val isCompleted: Boolean,
    val priority: OptionsPriorityType?
)