package com.plaugig.todo2.ui.fragment.redact.task.item

abstract class EditTaskItem {
    abstract fun isItemTheSame(item: EditTaskItem): Boolean
    abstract fun isContentTheSame(item: EditTaskItem): Boolean
    abstract fun getPayload(item: EditTaskItem): Any
}