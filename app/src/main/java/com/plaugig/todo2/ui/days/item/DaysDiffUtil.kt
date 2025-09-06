package com.plaugig.todo2.ui.days.item

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by George on 9/6/25.
 */
class DaysDiffUtil(
	val oldItems: List<DayItemState>,
	val newItems: List<DayItemState>
) : DiffUtil.Callback() {

	override fun getOldListSize(): Int = oldItems.size
	override fun getNewListSize(): Int = newItems.size


	override fun areItemsTheSame(
		oldItemPosition: Int,
		newItemPosition: Int
	): Boolean {
		val oldItem = oldItems[oldItemPosition]
		val newItem = newItems[newItemPosition]

		return oldItem.id == newItem.id
	}

	override fun areContentsTheSame(
		oldItemPosition: Int,
		newItemPosition: Int
	): Boolean {
		val oldItem = oldItems[oldItemPosition]
		val newItem = newItems[newItemPosition]

		return oldItem.nameOfDay == newItem.nameOfDay
				&& oldItem.numberOfDay == newItem.numberOfDay
	}

	override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
		val oldItem = oldItems[oldItemPosition]
		val newItem = newItems[newItemPosition]

		return DayItemStatePayload(
			isNameOfDayChanged = oldItem.nameOfDay != newItem.nameOfDay,
			isNumberOfDayChanged = oldItem.numberOfDay != newItem.numberOfDay
		)
	}
}