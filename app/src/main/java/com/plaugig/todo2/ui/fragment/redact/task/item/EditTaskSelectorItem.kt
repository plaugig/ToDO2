package com.plaugig.todo2.ui.fragment.redact.task.item

data class EditTaskSelectorItem(
    val items: List<String>
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
