package com.plaugig.todo2.ui.days

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.R

class DaysItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val offsetM = context.resources.getDimension(R.dimen.offset_m).toInt()
    private val offsetL = context.resources.getDimension(R.dimen.offset_l).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: 0
        val position = parent.getChildAdapterPosition(view)

        val left: Int
        val right: Int

        when (position) {
            0 -> {
                left = offsetL
                right = offsetM
            }
            itemCount - 1 -> {
                left = 0
                right = offsetL
            }
            else -> {
                left = 0
                right = offsetM
            }
        }

        outRect.set(left, 0, right, 0)
    }
}