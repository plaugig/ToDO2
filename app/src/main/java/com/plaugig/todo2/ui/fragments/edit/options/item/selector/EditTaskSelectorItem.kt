package com.plaugig.todo2.ui.fragments.edit.options.item.selector

import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItem
import com.plaugig.todo2.ui.fragments.edit.options.item.selector.item.SelectorItemData

data class EditTaskSelectorItem(
    val items: List<SelectorItemData>
) : EditTaskItem() {
    override fun isItemTheSame(item: EditTaskItem): Boolean {
        return item is EditTaskSelectorItem
    }

    override fun isContentTheSame(item: EditTaskItem): Boolean {
        item as EditTaskSelectorItem

        return items == item.items
    }

    override fun getPayload(item: EditTaskItem): Any {
        item as EditTaskSelectorItem

        return EditTaskSelectorItemPayload(
            areItemsTheSame = items != item.items
        )
    }
}
