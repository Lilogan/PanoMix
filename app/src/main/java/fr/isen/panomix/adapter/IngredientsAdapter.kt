package fr.isen.panomix.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import fr.isen.panomix.R
import fr.isen.panomix.model.Ingredient
import fr.isen.panomix.ui.storage.StorageFragment

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
        private var itemUnit: TextView = itemView.findViewById(R.id.ingredientUnitTextView)
        private var itemQuantity: TextView = itemView.findViewById(R.id.ingredientQuantityTextView)

        init {
            itemView.setOnLongClickListener{
                val pop = PopupMenu(itemView.context,it)
                pop.inflate(R.menu.popup_menu)
                pop.setOnMenuItemClickListener { item->
                    when(item.itemId)
                    {
                        R.id.change_Quantity->{
                            
                        }
                        R.id.delete_ing->{

                        }
                    }
                    true
                }
                pop.show()
                true
            }
        }

        fun setUpIngredient(ingredient: Ingredient?) {
            itemName.text = ingredient?.name
            itemQuantity.text = ingredient?.quantity.toString()
            itemUnit.text = ingredient?.unit
        }

        override fun onClick(v: View?) {
            itemClickListener?.onItemClick(adapterPosition, v)
        }
    }

}

