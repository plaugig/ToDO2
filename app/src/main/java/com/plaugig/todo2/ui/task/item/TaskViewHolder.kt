package com.plaugig.todo2.ui.task.item

import android.graphics.Paint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.R
import com.plaugig.todo2.databinding.TaskItemBinding
import com.plaugig.todo2.ui.MainEventListener

class TaskViewHolder(
	itemView: View,
	private val listener: MainEventListener
) : RecyclerView.ViewHolder(itemView) {

	private var binding = TaskItemBinding.bind(itemView)

	private var id: Int? = null


	fun bind(state: TaskItemData) {
		id = state.id

		binding.nameTask.text = itemView.resources.getString(state.nameRes)
		binding.textTask.text = itemView.resources.getString(state.textRes)

		binding.checkbox.setImageResource(
			if (state.isDone) {
				R.drawable.ic_checked_box
			} else {
				R.drawable.check_box_task
			}
		)

		binding.root.setCardBackgroundColor(
			ContextCompat.getColor(
				binding.root.context,
				if (state.isDone) {
					R.color.task_color_true
				} else {
					R.color.task_color_false
				}
			)
		)

		if (state.isDone) {
			binding.textTask.paintFlags = binding.textTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
		} else {
			binding.textTask.paintFlags =
				binding.textTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
		}

		setClickListener(state)
	}

	fun bind(state: TaskItemData, payload: Any?) {
		payload as TaskItemStatePayload

		if (payload.isNameChanged) {
            itemView.resources.getString(state.nameRes)
		}
		if (payload.isTextChanged) {
            itemView.resources.getString(state.textRes)
		}
		if (payload.isDoneStateChanged) {
			binding.checkbox.setImageResource(
				if (state.isDone) {
					R.drawable.ic_checked_box
				} else {
					R.drawable.check_box_task
				}
			)

			binding.root.setCardBackgroundColor(
				ContextCompat.getColor(
					binding.root.context,
					if (state.isDone) {
						R.color.task_color_true
					} else {
						R.color.task_color_false
					}
				)
			)

			if (state.isDone) {
				binding.textTask.paintFlags = binding.textTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
			} else {
				binding.textTask.paintFlags =
					binding.textTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
			}

			setClickListener(state)
		}
	}

	private fun setClickListener(state: TaskItemData) {
		binding.checkbox.setOnClickListener {
			listener.onTaskDone(state.id, state.isDone)
		}

		binding.root.setOnClickListener {
			listener.onOpenTask(state.id)
		}
	}

	fun onDelete() {
		id?.let {
			listener.onDeleteTask(it)
		}
	}
}