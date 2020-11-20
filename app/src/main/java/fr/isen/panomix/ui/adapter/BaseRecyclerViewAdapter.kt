package fr.isen.panomix.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import fr.isen.panomix.utils.OnItemClickListener

abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var list: List<T>? = emptyList()
    protected var itemClickListener: OnItemClickListener<T>? = null

    fun setItems(items: List<T>) {
        this.list = items
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T? {
        return this.list?.get(position)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>) {
        this.itemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int = list!!.size

}