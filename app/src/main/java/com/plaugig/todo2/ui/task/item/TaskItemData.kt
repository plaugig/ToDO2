package com.plaugig.todo2.ui.task.item

import androidx.annotation.StringRes

data class TaskItemData(
	val id: Int,
	@StringRes val nameRes: Int,
	@StringRes val textRes: Int,
	val isDone: Boolean
)
