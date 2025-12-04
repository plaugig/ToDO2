package com.plaugig.todo2.ui.fragments.edit.options.item.selector.item

import androidx.annotation.ColorRes
import com.plaugig.todo2.domain.options.priority.OptionsPriorityType

data class SelectorItemData(
    val id: Int,
    val title: String,
    @ColorRes val color: Int,
    val priority: OptionsPriorityType?,
    val isSelected: Boolean
)