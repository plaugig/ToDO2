package com.plaugig.todo2.ui.task


import com.plaugig.todo2.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.ui.MainViewModel

class SwipeDeliteTask(private val viewModel: MainViewModel, context: Context) :
    ItemTouchHelper.SimpleCallback
        (0, ItemTouchHelper.LEFT) {

    private val background_ = ColorDrawable(ContextCompat.getColor(context, R.color.red))
    private val clearPaint = Paint()

    private val deleteIcon =
        ContextCompat.getDrawable(context, R.drawable.basket)


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(
        viewHolder: RecyclerView.ViewHolder,
        direction: Int
    ) {
        val position = viewHolder.bindingAdapterPosition
        if (position == RecyclerView.NO_POSITION) return

        val taskId = viewModel.tasks.value?.get(position)?.id ?: return
        viewModel.onDeleteTask(taskId)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView

        if (dX < 0) {
            val left = (itemView.right + dX).toInt()
            val right = itemView.right
            val top = itemView.top
            val bottom = itemView.bottom

            background_.setBounds(left, top, right, bottom)
            background_.draw(c)

            deleteIcon?.let { icon ->
                val intrinsicWidth = icon.intrinsicWidth
                val intrinsicHeight = icon.intrinsicHeight


                val iconTop = itemView.top + (itemView.height - intrinsicHeight) / 2
                val iconBottom = iconTop + intrinsicHeight
                val iconRight = itemView.right - (itemView.height - intrinsicHeight )
                val iconLeft = iconRight + intrinsicHeight

                icon.setBounds(iconLeft , iconTop , iconRight ,  iconBottom)
                icon.draw(c)

            }

        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

    }
}