package com.plaugig.todo2.ui.task.item

import androidx.recyclerview.widget.DiffUtil

class TaskDiffUtil(
    val oldItem: List<TaskItemData>,
    val newItem: List<TaskItemData>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItem.size
    override fun getNewListSize(): Int = newItem.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem = oldItem[oldItemPosition]
        val newItem = newItem[newItemPosition]

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem = oldItem[oldItemPosition]
        val newItem = newItem[newItemPosition]

        return oldItem.nameRes == newItem.nameRes
                && oldItem.textRes == newItem.textRes
                && oldItem.isDone == newItem.isDone
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldItem[oldItemPosition]
        val newItem = newItem[newItemPosition]

        return TaskItemStatePayload (
            isNameChanged = oldItem.nameRes != newItem.nameRes,
            isTextChanged = oldItem.textRes != newItem.textRes,
            isDoneStateChanged = oldItem.isDone != newItem.isDone
        )
    }
}