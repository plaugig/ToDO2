package com.plaugig.todo2.ui.fragment.redact.task.item

import androidx.recyclerview.widget.DiffUtil

class EditTaskItemDiffUtil(
    private val oldItems: List<EditTaskItem>,
    private val newItems: List<EditTaskItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size
    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem.isItemTheSame(newItem)
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem.isContentTheSame(newItem)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}