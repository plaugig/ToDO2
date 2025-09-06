package com.plaugig.todo2.ui.days

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.R
import com.plaugig.todo2.ui.days.item.DayItemState
import com.plaugig.todo2.ui.days.item.DayViewHolder
import com.plaugig.todo2.ui.days.item.DaysDiffUtil

/**
 * Created by George on 9/6/25.
 */
class DaysAdapter : RecyclerView.Adapter<DayViewHolder>() {

	var days: List<DayItemState> = emptyList()
		set(value) {
			val callback = DaysDiffUtil(field, value)
			val difference = DiffUtil.calculateDiff(callback)
			field = value
			difference.dispatchUpdatesTo(this)
		}


	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): DayViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(
			R.layout.day_item,
			parent,
			false
		)

		return DayViewHolder(itemView)
	}

	override fun onBindViewHolder(
		holder: DayViewHolder,
		position: Int
	) {
		holder.bind(days[position])
	}

	override fun onBindViewHolder(
		holder: DayViewHolder,
		position: Int,
		payloads: List<Any?>
	) {
		if (payloads.isEmpty()) {
			onBindViewHolder(holder, position)
		} else {
			val dayState = days[position]

			payloads.forEach { payload ->
				holder.bind(dayState, payload)
			}
		}
	}

	override fun getItemCount(): Int = days.size
}