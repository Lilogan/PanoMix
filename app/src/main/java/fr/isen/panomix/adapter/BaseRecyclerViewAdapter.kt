package fr.isen.panomix.adapter

import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.panomix.`interface`.OnItemClickListener

abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<T>? = emptyList()
    protected var itemClickListener: OnItemClickListener? = null

    fun setItems(items: List<T>) {
        this.list = items
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T? {
        return this.list?.get(position)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int = list!!.size

}