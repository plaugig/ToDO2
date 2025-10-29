package com.plaugig.todo2.ui.fragments.edit.options.item.selector.item

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.R

class SelectorItemDecoration(context: Context): RecyclerView.ItemDecoration() {
    private val ofsetXS = context.resources.getDimension(R.dimen.offset_xs ).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        var left = 0
        var right = 0

        when(position){
            1 -> {
                left = ofsetXS
                right = ofsetXS
            }
        }
        outRect.set(left,0,right,0)
    }

}