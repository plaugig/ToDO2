package com.plaugig.todo2.ui.fragments.edit

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.R

class EditItemDecoration(context: Context): RecyclerView.ItemDecoration() {
    private val offsetM = context.resources.getDimension(R.dimen.offset_m).toInt()
    private val offsetL = context.resources.getDimension(R.dimen.offset_l).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)


        var bottom = 0

        when(position){
            0 -> {
                bottom = offsetM
            }
            1 -> {
                bottom = offsetL
            }
        }
        outRect.set(0,0,0,bottom)
    }

}