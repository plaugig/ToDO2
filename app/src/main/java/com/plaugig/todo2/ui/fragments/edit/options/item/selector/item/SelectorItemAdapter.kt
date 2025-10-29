package com.plaugig.todo2.ui.fragments.edit.options.item.selector.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.plaugig.todo2.R

class SelectorItemAdapter(
    private var items: List<SelectorItemData> ,
    /*private val visibleCount : Int*/
): RecyclerView.Adapter<SelectorItemViewHolder>(){

        var currentItems = items.toList()
            set(value) {
                val callback = SelectorItemDiffUtil(field,value)
                val difference = DiffUtil.calculateDiff(callback)
                field = value
                difference.dispatchUpdatesTo(this)
            }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectorItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.edit_task_selector_item,
            parent,
            false
        )

       /* itemView.post {
            val layoutParams = itemView.layoutParams
            layoutParams.width = parent.width / visibleCount
            itemView.layoutParams = layoutParams
        }*/

        return SelectorItemViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: SelectorItemViewHolder,
        position: Int
    ) {
        holder.bind(currentItems[position])
/*
        val recycler = holder.itemView.findViewById<RecyclerView>(R.id.recycler)

        if (recycler.itemDecorationCount == 0){
            recycler.addItemDecoration(SelectorItemDecoration(holder.itemView.context))
        }*/
    }

    override fun onBindViewHolder(
        holder: SelectorItemViewHolder,
        position: Int,
        payloads: List<Any?>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder,position)
        } else {
            payloads.forEach{ payloads ->
                holder.bind(currentItems[position], payloads)
            }
        }
    }

    override fun getItemCount(): Int = currentItems.size

}