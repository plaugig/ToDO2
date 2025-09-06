package com.plaugig.todo2.ui.days.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.databinding.DayItemBinding

/**
 * Created by George on 9/6/25.
 */
class DayViewHolder(
	itemView: View
) : RecyclerView.ViewHolder(itemView) {

	private val binding = DayItemBinding.bind(itemView)


	fun bind(state: DayItemState) {
		binding.name.text = state.nameOfDay
		binding.number.text = state.numberOfDay
	}

	fun bind(state: DayItemState, payload: Any?) {
		payload as DayItemStatePayload

		if (payload.isNameOfDayChanged) {
			binding.name.text = state.nameOfDay
		}

		if (payload.isNumberOfDayChanged) {
			binding.number.text = state.numberOfDay
		}
	}
}