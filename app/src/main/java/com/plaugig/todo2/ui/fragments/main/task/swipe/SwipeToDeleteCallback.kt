package com.plaugig.todo2.ui.fragments.main.task.swipe

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.R
import com.plaugig.todo2.ui.fragments.main.task.item.TaskViewHolder

class SwipeToDeleteCallback(
	context: Context
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

	private val offsetL = context.resources.getDimensionPixelSize(R.dimen.offset_l)
	private val radius = context.resources.getDimension(R.dimen.default_radius)

	private val deleteIcon = AppCompatResources.getDrawable(context, R.drawable.ic_delete)

	private val intrinsicWidth = deleteIcon?.intrinsicWidth ?: 0
	private val intrinsicHeight = deleteIcon?.intrinsicHeight ?: 0

	private val backgroundDrawable = GradientDrawable().apply {
		shape = GradientDrawable.RECTANGLE
		cornerRadius = radius
		setColor(ContextCompat.getColor(context, R.color.red))
	}

	private val clearPaint = Paint().apply {
		Paint.setXfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
	}


	override fun onChildDraw(
		canvas: Canvas,
		recyclerView: RecyclerView,
		viewHolder: RecyclerView.ViewHolder,
		dX: Float,
		dY: Float,
		actionState: Int,
		isCurrentlyActive: Boolean
	) {
		val itemView = viewHolder.itemView
		val itemHeight = itemView.bottom - itemView.top
		val isCanceled = dX == 0f && !isCurrentlyActive

		if (isCanceled) {
			clearCanvas(canvas, itemView.right + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
			super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, false)
			return
		}

		// Draw the red delete background
		backgroundDrawable.setBounds(
			itemView.right - offsetL + dX.toInt(),
			itemView.top,
			itemView.right,
			itemView.bottom
		)
		backgroundDrawable.draw(canvas)

		// Calculate position of delete icon
		val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
		val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
		val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
		val deleteIconRight = itemView.right - deleteIconMargin
		val deleteIconBottom = deleteIconTop + intrinsicHeight

		// Draw the delete icon
		deleteIcon?.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
		deleteIcon?.draw(canvas)

		super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
	}

	override fun onSwiped(
		viewHolder: RecyclerView.ViewHolder,
		direction: Int
	) {
		viewHolder as TaskViewHolder

		viewHolder.onDelete()
	}


	private fun clearCanvas(c: Canvas?, left: Float, top: Float, right: Float, bottom: Float) {
		c?.drawRect(left, top, right, bottom, clearPaint)
	}



	override fun onMove(
		recyclerView: RecyclerView,
		viewHolder: RecyclerView.ViewHolder,
		target: RecyclerView.ViewHolder
	): Boolean {
		return false // unused
	}
}