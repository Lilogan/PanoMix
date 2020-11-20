package fr.isen.panomix.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import fr.isen.panomix.R
import fr.isen.panomix.data.model.Ingredient
import fr.isen.panomix.utils.OnItemClickListener

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

    inner class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var itemCard: CardView = itemView.findViewById(R.id.cardIngredient)
        private var itemCheckBox: CheckBox = itemView.findViewById(R.id.ingredientCheckBox)

        fun setUpIngredient(ingredient: Ingredient?) {
            itemCheckBox.text = ingredient?.name
            itemCheckBox.isChecked = ingredient?.available!!

            if (!itemCheckBox.isChecked) {
                itemCard.setCardBackgroundColor(
                    ResourcesCompat.getColor(
                        itemView.resources,
                        R.color.colorWarning,
                        null
                    )
                )
            } else {
                itemCard.setCardBackgroundColor(
                    ResourcesCompat.getColor(
                        itemView.resources,
                        R.color.colorAccentDark,
                        null
                    )
                )
            }

            itemView.setOnClickListener {
                itemClickListener?.onItemClick(ingredient, adapterPosition)
            }

        }

    }

}



