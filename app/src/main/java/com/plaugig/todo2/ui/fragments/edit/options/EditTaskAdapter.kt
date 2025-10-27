package com.plaugig.todo2.ui.fragments.edit.options

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.R
import com.plaugig.todo2.ui.fragments.edit.options.item.EditTaskItemDiffUtil
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItem
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItemViewHolder
import com.plaugig.todo2.ui.fragments.edit.options.item.button.EditTaskButtonItem
import com.plaugig.todo2.ui.fragments.edit.options.item.button.EditTaskButtonItemViewHolder
import com.plaugig.todo2.ui.fragments.edit.options.item.selector.EditTaskSelectorItem
import com.plaugig.todo2.ui.fragments.edit.options.item.selector.EditTaskSelectorItemViewHoldet

class EditTaskAdapter() : RecyclerView.Adapter<EditTaskItemViewHolder>() {

    var items = emptyList<EditTaskItem>()
        set(value) {
            val callback = EditTaskItemDiffUtil(
	            oldItems = field,
	            newItems = value
            )
            val result = DiffUtil.calculateDiff(callback)
            field = value
            result.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(
	    parent: ViewGroup,
	    viewType: Int
    ): EditTaskItemViewHolder {
        return when (viewType) {
            EditActionItemType.BUTTON -> {
                val itemView = LayoutInflater.from(parent.context).inflate(
                    R.layout.edit_task_button_item,
                    parent,
                    false
                )
                EditTaskButtonItemViewHolder(itemView)
            }

            EditActionItemType.SELECTOR -> {
                val itemView = LayoutInflater.from(parent.context).inflate(
                    R.layout.edit_task_selector_recycler_item,
                    parent,
                    false
                )
                EditTaskSelectorItemViewHoldet(itemView)
            }

            else -> error("Invalid view type for view holder.!.")
        }
    }

    override fun onBindViewHolder(
	    holder: EditTaskItemViewHolder,
	    position: Int
    ) {
        holder.bind(items[position])
    }

    override fun onBindViewHolder(
        holder: EditTaskItemViewHolder,
        position: Int,
        payloads: List<Any?>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            payloads.forEach { payload ->
                holder.bind(items[position], payload)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is EditTaskButtonItem -> EditActionItemType.BUTTON
            is EditTaskSelectorItem -> EditActionItemType.SELECTOR
            else -> error("Invalid item for view type.!.")
        }
    }
}