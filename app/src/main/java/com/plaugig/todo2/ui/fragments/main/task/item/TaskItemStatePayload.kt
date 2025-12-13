package com.plaugig.todo2.ui.fragments.main.task.item

data class TaskItemStatePayload(
	val isNameChanged: Boolean,
	val isTextChanged: Boolean,
	val isDoneStateChanged: Boolean,
    val isPriorityChanged: Boolean
)
