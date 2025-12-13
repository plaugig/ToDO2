package com.plaugig.todo2.ui.fragments.edit.options.item.selector.item

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.databinding.EditTaskSelectorItemBinding
import com.plaugig.todo2.ui.fragments.edit.EditTaskListener

class SelectorItemViewHolder(
    itemView: View,
    private val listener: EditTaskListener
) : RecyclerView.ViewHolder(itemView) {

    private var binding = EditTaskSelectorItemBinding.bind(itemView)


    fun bind(item: SelectorItemData) {
        binding.selector.text = item.title
        binding.selectorIndicator.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                binding.root.context, item.color
            )
        )
        binding.prirority.isSelected = item.isSelected

        binding.prirority.setOnClickListener {
            listener.onPrioritySelected(item.type)
        }
    }

    fun bind(item: SelectorItemData, payload: Any?) {
        payload as SelectorItemPayload

        if (payload.isTitleChanged) {
            binding.selector.text = item.title
        }

        if (payload.isColorChanged) {
            binding.selectorIndicator.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    binding.root.context, item.color
                )
            )
        }

        if (payload.isSelectionChanged) {
            binding.prirority.isSelected = item.isSelected
        }
    }
}