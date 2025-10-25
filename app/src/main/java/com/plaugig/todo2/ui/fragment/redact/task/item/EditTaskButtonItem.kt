package com.plaugig.todo2.ui.fragment.redact.task.item

data class EditTaskButtonItem(
    val title: String,
    val description: String
) : EditTaskItem() {

    override fun isItemTheSame(item: EditTaskItem): Boolean {
        return item is EditTaskButtonItem
    }

    override fun isContentTheSame(item: EditTaskItem): Boolean {
        item as EditTaskButtonItem

        return title == item.title &&
                description == item.description
    }

    override fun getPayload(item: EditTaskItem): Any {
        item as EditTaskButtonItem

        return EditTaskButtonItemPayload(
            isTitleChanged = title != item.title,
            isDescriptionChanged = description != item.description
        )

    }

}