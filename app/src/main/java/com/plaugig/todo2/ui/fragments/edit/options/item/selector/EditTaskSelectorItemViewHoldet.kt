package com.plaugig.todo2.ui.fragments.edit.options.item.selector

import android.view.View
import com.plaugig.todo2.databinding.EditTaskSelectorRecyclerItemBinding
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItem
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItemViewHolder
import com.plaugig.todo2.ui.fragments.edit.options.item.selector.item.SelectorItemAdapter

class EditTaskSelectorItemViewHoldet (itemView : View) : EditTaskItemViewHolder(itemView){

    private val binding = EditTaskSelectorRecyclerItemBinding.bind(itemView)


    override fun bind(item: EditTaskItem) {
        item as EditTaskSelectorItem
        binding.recycler.adapter = SelectorItemAdapter(item.items)


    }

    override fun bind(
        item: EditTaskItem,
        payload: Any
    ) {
        item as EditTaskSelectorItem
        payload as EditTaskSelectorItemPayload

        if (payload.areItemsTheSame){
            binding.recycler.adapter = SelectorItemAdapter(item.items)
        }
    }
}