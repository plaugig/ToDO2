package com.plaugig.todo2.ui.fragments.edit.options.item.button

import android.view.View
import com.plaugig.todo2.databinding.EditTaskButtonItemBinding
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItem
import com.plaugig.todo2.ui.fragments.edit.options.item.base.EditTaskItemViewHolder

class EditTaskButtonItemViewHolder(itemView: View): EditTaskItemViewHolder(itemView) {

    private val binding = EditTaskButtonItemBinding.bind(itemView)

    override fun bind(item: EditTaskItem) {
        item as EditTaskButtonItem

        binding.title.text = item.title
        binding.description.text = item.description
    }

    override fun bind(
        item: EditTaskItem,
        payload: Any?
    ) {
        item as EditTaskButtonItem
        payload as EditTaskButtonItemPayload

        if (payload.isTitleChanged) {
            binding.title.text = item.title
        }

        if (payload.isDescriptionChanged) {
            binding.description.text = item.description
        }
    }
}