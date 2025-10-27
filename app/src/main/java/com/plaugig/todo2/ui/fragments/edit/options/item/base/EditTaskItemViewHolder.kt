package com.plaugig.todo2.ui.fragments.edit.options.item.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class EditTaskItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: EditTaskItem)
    abstract fun bind(item: EditTaskItem, payload: Any?)
}