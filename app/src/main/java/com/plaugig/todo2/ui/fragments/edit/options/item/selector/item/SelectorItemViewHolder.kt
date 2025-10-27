package com.plaugig.todo2.ui.fragments.edit.options.item.selector.item

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.databinding.EditTaskSelectorItemBinding

class SelectorItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private var binding = EditTaskSelectorItemBinding.bind(itemView)

    fun bind (item: SelectorItemData){

        binding.selector.text = item.title
        binding.selectorIndicator.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                binding.root.context, item.collor
            )
        )

    }

    fun bind (item: SelectorItemData, payload: Any?){
                payload as SelectorItemPayload

        if ( payload.isTitleChanged)
            binding.selector.text = item.title
             binding.selectorIndicator.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                binding.root.context, item.collor
            )
        )


    }

}