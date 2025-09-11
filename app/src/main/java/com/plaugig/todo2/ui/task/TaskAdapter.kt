package com.plaugig.todo2.ui.task


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.R
import com.plaugig.todo2.ui.task.item.TaskDiffUtil
import com.plaugig.todo2.ui.task.item.TaskItemData
import com.plaugig.todo2.ui.task.item.TaskViewHolder

class TaskAdapter(
    val onTaskClick : (TaskItemData, Int) -> Unit
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

        return TaskViewHolder(itemView)
    }


    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {
        val taskItem = task[position]
        holder.bind(taskItem)


        holder.itemView.setOnClickListener{
            onTaskClick(taskItem , position)
        }

    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int,
        payloads: List<Any?>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            val taskState = task[position]

            payloads.forEach { payload ->
                holder.bind(taskState, payload)
            }
        }


    }


    override fun getItemCount(): Int = task.size

    fun updateTask (position: Int , taskNew : TaskItemData){
        val newTask = task.toMutableList()
        newTask[position] = taskNew
        task = newTask
        notifyItemChanged(position)

    }
}