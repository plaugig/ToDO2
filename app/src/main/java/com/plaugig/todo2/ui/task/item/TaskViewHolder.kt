package com.plaugig.todo2.ui.task.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.databinding.TaskItemBinding
import com.plaugig.todo2.ui.days.item.DayItemState

class TaskViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding = TaskItemBinding.bind(itemView)

    fun bind (state: TaskItemData) {
        binding.nameTask.text = state.name
        binding.textTask.text = state.text
    }

    fun bind (state: TaskItemData, payload: Any?){
        payload as TaskItemStatePayload

        if (payload.isName_task){
            binding.nameTask.text = state.name
        }
        if (payload.isText_task){
            binding.textTask.text = state.text
        }
    }
}