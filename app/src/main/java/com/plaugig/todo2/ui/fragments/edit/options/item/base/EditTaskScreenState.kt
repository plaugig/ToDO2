package com.plaugig.todo2.ui.fragments.edit.options.item.base

import com.plaugig.todo2.domain.options.priority.OptionsPriorityType

data class EditTaskScreenState(
    val id: Int,
    val title: String,
    val description: String,
    val priority: OptionsPriorityType?
)
