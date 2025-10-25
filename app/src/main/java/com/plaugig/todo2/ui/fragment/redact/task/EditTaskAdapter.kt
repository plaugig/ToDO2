package com.plaugig.todo2.ui.fragment.redact.task

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.ui.fragment.redact.task.item.EditActionItemType
import com.plaugig.todo2.ui.fragment.redact.task.item.EditTaskButtonItem
import com.plaugig.todo2.ui.fragment.redact.task.item.EditTaskItem
import com.plaugig.todo2.ui.fragment.redact.task.item.EditTaskItemDiffUtil
import com.plaugig.todo2.ui.fragment.redact.task.item.EditTaskViewHolder
import com.plaugig.todo2.ui.fragment.redact.task.item.EditTaskItemViewHolder
import com.plaugig.todo2.ui.fragment.redact.task.item.EditTaskSelectorItem

class EditTaskAdapter() : RecyclerView.Adapter<EditTaskViewHolder>() {

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
    ): EditTaskViewHolder {

    }

    override fun onBindViewHolder(
        holder: EditTaskViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is EditTaskButtonItem -> EditActionItemType.BUTTON
            is EditTaskSelectorItem -> EditActionItemType.SELECTOR
            else -> error("Invalid item for view type!")
        }
    }
}