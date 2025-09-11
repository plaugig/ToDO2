package com.plaugig.todo2.ui.task

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.R

class TaskItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val offsetT = context.resources.getDimension(R.dimen.offset_top).toInt()
    private val offsetB = context.resources.getDimension(R.dimen.offset_bottom).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: 0
        val pisition = parent.getChildAdapterPosition(view)

        val top: Int
        val bottom: Int

        when (pisition) {
            0 -> {
                top = offsetT
                bottom = offsetB
            }

            itemCount - 1 -> {
                top = 0
                bottom = offsetT
            }

            else -> {
                top = 0
                bottom = offsetB
            }
        }

        outRect.set(0 , top , 0 , bottom)

    }


}