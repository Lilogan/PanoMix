package fr.isen.panomix.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.panomix.R
import fr.isen.panomix.model.Ingredient

class IngredientsAdapter(context: Context) :
    RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var ingredients = emptyList<Ingredient>()

    inner class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById(R.id.ingredientNameTextView)
        var itemUnit: TextView = itemView.findViewById(R.id.ingredientUnitTextView)
        var itemQuantity: TextView = itemView.findViewById(R.id.ingredientQuantityTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val itemView = inflater.inflate(R.layout.card_ingredient, parent, false)
        return IngredientViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.itemName.text = ingredients[position].name
        holder.itemUnit.text = ingredients[position].unit
        holder.itemQuantity.text = ingredients[position].quantity.toString()
    }

    internal fun setIngredients(ingredients: List<Ingredient>) {
        this.ingredients = ingredients
        notifyDataSetChanged()
    }

    override fun getItemCount() = ingredients.size

}