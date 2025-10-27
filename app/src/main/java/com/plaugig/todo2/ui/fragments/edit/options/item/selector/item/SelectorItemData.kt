package com.plaugig.todo2.ui.fragments.edit.options.item.selector.item

import androidx.annotation.ColorRes

data class SelectorItemData(
    val id: Int,
    val title: String,
    @ColorRes val collor: Int
)