package fr.isen.panomix.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import fr.isen.panomix.R
import fr.isen.panomix.data.model.Ingredient

class RecipeAdapter : BaseRecyclerViewAdapter<Ingredient>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_ingredient, parent, false)
        return RecipeViewHolder(itemView)
    }

    
    override fun getItemCount(): Int {
        return 2
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //val viewHolder = holder as? RecipeViewHolder
        //viewHolder?.setUpIngredient(ingredient = getItem(position))
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var itemCard: CardView = itemView.findViewById(R.id.cardIngredient)


    }

}