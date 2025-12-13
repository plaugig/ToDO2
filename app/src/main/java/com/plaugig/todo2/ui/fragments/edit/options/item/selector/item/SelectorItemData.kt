package com.plaugig.todo2.ui.fragments.edit.options.item.selector.item

import androidx.annotation.ColorRes
import com.plaugig.todo2.domain.options.priority.OptionsPriorityType

data class SelectorItemData(
    val type: OptionsPriorityType,
    val title: String,
    @ColorRes val color: Int,
    val isSelected: Boolean
)