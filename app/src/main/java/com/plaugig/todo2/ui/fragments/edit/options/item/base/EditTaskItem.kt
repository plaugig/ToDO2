package com.plaugig.todo2.ui.fragments.edit.options.item.base

abstract class EditTaskItem {
    abstract fun isItemTheSame(item: EditTaskItem): Boolean
    abstract fun isContentTheSame(item: EditTaskItem): Boolean
    abstract fun getPayload(item: EditTaskItem): Any
}