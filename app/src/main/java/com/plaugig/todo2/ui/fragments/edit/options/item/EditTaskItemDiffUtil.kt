package com.plaugig.todo2.ui.fragments.edit.options.item

import androidx.recyclerview.widget.DiffUtil
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItem

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
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem.getPayload(newItem)
    }
}