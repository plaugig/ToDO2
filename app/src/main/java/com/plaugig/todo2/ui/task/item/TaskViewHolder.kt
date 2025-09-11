package com.plaugig.todo2.ui.task.item

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.R
import com.plaugig.todo2.databinding.TaskItemBinding
import com.plaugig.todo2.ui.days.item.DayItemState

class TaskViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding = TaskItemBinding.bind(itemView)

    fun bind (state: TaskItemData) {
        binding.nameTask.text = state.name
        binding.textTask.text = state.text

        binding.checkbox.setImageResource(
            if(state.isDone) {
                R.drawable.ic_checked_box
            } else {
                R.drawable.check_box_task
            }
        )

        binding.root.setBackgroundColor(
            ContextCompat.getColor(
                binding.root.context,
                if (state.isDone) {
                    R.color.task_color_true
                }
                else {
                    R.color.task_color_false
                }
            )
        )
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