package com.plaugig.todo2.ui.task.item

import androidx.recyclerview.widget.DiffUtil

class TaskDiffUtil(
    val oldItem: List<TaskItemData>,
    val newItem: List<TaskItemData>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItem.size
    override fun getNewListSize(): Int = newItem.size

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem = oldItem[oldItemPosition]
        val newItem = newItem[oldItemPosition]

        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem = oldItem[oldItemPosition]
        val newItem = newItem[newItemPosition]

        return oldItem.name == newItem.name
                && oldItem.text == newItem.text
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldItem[oldItemPosition]
        val newItem = newItem[newItemPosition]

        return TaskItemStatePayload (
            isNameChanged = oldItem.name != newItem.name,
            isTextChanged = oldItem.text != newItem.text
        )
    }

}