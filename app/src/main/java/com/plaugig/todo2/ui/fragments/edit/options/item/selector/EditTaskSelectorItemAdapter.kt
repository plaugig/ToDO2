package com.plaugig.todo2.ui.fragments.edit.options.item.selector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.R
import com.plaugig.todo2.domain.options.priority.OptionsPriorityType
import com.plaugig.todo2.ui.fragments.edit.EditTaskListener
import com.plaugig.todo2.ui.fragments.edit.options.item.selector.item.SelectorItemData
import com.plaugig.todo2.ui.fragments.edit.options.item.selector.item.SelectorItemDiffUtil
import com.plaugig.todo2.ui.fragments.edit.options.item.selector.item.SelectorItemViewHolder

class EditTaskSelectorItemAdapter(
    private val listener: EditTaskListener
) : RecyclerView.Adapter<SelectorItemViewHolder>() {

    var items = emptyList<SelectorItemData>()
        set(value) {
            val callback = SelectorItemDiffUtil(field, value)
            val difference = DiffUtil.calculateDiff(callback)
            field = value
            difference.dispatchUpdatesTo(this)
        }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectorItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.edit_task_selector_item,
            parent,
            false
        )

        return SelectorItemViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(
        holder: SelectorItemViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun onBindViewHolder(
        holder: SelectorItemViewHolder,
        position: Int,
        payloads: List<Any?>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            payloads.forEach { payloads ->
                holder.bind(items[position], payloads)
            }
        }
    }

    override fun getItemCount(): Int = items.size

}