package com.plaugig.todo2.ui.fragments.edit.options.item.selector.item

import androidx.recyclerview.widget.DiffUtil

class SelectorItemDiffUtil(
    private val oldItems: List<SelectorItemData>,
    private val newItems: List<SelectorItemData>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size
    override fun getNewListSize(): Int = oldItems.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem.type == newItem.type
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem.title == newItem.title
                && oldItem.color == newItem.color
                && oldItem.isSelected == newItem.isSelected
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return SelectorItemPayload(
            isTitleChanged = oldItem.title != newItem.title,
            isColorChanged = oldItem.color != newItem.color,
            isSelectionChanged = oldItem.isSelected != newItem.isSelected
        )
    }
}