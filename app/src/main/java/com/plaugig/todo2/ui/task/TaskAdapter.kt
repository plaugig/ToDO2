package com.plaugig.todo2.ui.task


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.R
import com.plaugig.todo2.ui.fragments.main.MainEventListener
import com.plaugig.todo2.ui.task.item.TaskDiffUtil
import com.plaugig.todo2.ui.task.item.TaskItemData
import com.plaugig.todo2.ui.task.item.TaskViewHolder

class TaskAdapter(
    private val listener: MainEventListener ,
) : RecyclerView.Adapter<TaskViewHolder>() {

    var task: List<TaskItemData> = emptyList()
        set(value) {
            val callback = TaskDiffUtil(field, value)
            val difference = DiffUtil.calculateDiff(callback)
            field = value
            difference.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.task_item,
            parent,
            false
        )

        return TaskViewHolder(itemView, listener)
    }


    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {
        holder.bind(task[position])
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int,
        payloads: List<Any?>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            payloads.forEach { payload ->
                holder.bind(task[position], payload)
            }
        }
    }

    override fun getItemCount(): Int = task.size
}