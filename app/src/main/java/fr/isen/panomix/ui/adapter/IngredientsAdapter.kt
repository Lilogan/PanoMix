package fr.isen.panomix.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.panomix.R
import fr.isen.panomix.data.model.Ingredient

class IngredientsAdapter() : BaseRecyclerViewAdapter<Ingredient>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_ingredient, parent, false)
        return IngredientViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as? IngredientViewHolder
        viewHolder?.setUpIngredient(ingredient = getItem(position))
    }

    inner class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private var itemName: TextView = itemView.findViewById(R.id.ingredientNameTextView)

        fun setUpIngredient(ingredient: Ingredient?) {
            itemName.text = ingredient?.name
        }

        override fun onClick(v: View?) {
            itemClickListener?.onItemClick(adapterPosition, v)
        }
    }

}

