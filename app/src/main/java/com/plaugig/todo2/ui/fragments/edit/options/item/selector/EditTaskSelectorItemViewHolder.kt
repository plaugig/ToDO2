package com.plaugig.todo2.ui.fragments.edit.options.item.selector

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.plaugig.todo2.databinding.EditTaskSelectorRecyclerItemBinding
import com.plaugig.todo2.domain.options.priority.OptionsPriorityType
import com.plaugig.todo2.ui.fragments.edit.EditTaskListener
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItem
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItemViewHolder
import com.plaugig.todo2.ui.fragments.edit.options.item.selector.item.SelectorItemDecoration

class EditTaskSelectorItemViewHolder (
    itemView : View,
    listener: EditTaskListener
) : EditTaskItemViewHolder(itemView){

    private val binding = EditTaskSelectorRecyclerItemBinding.bind(itemView)

    private var adapter = EditTaskSelectorItemAdapter(listener)

    override fun bind(item: EditTaskItem) {
        item as EditTaskSelectorItem
        binding.recycler.adapter = adapter
        binding.recycler.addItemDecoration(SelectorItemDecoration(itemView.context))
        binding.recycler.layoutManager = GridLayoutManager(
            itemView.context,
            3,
            GridLayoutManager.VERTICAL,
            false
        )
        adapter.items = item.items
    }

    override fun bind(
        item: EditTaskItem,
        payload: Any?
    ) {
        item as EditTaskSelectorItem
        payload as EditTaskSelectorItemPayload

        if (payload.areItemsTheSame){
            adapter.items = item.items
        }
    }
}