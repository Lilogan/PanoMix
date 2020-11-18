package fr.isen.panomix.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.panomix.R
import fr.isen.panomix.model.Cocktail

class BarAdapter() : BaseRecyclerViewAdapter<Cocktail>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_cocktail, parent, false)
        return BarViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as? BarViewHolder
        viewHolder?.setUpCocktail(cocktail = getItem(position), viewHolder =  viewHolder)
    }

    inner class BarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private var itemName: TextView = itemView.findViewById(R.id.cocktailTextView)
        private var itemImg: ImageView = itemView.findViewById(R.id.cocktailImageView)

        init {
            itemView.setOnClickListener(this)
        }

        fun setUpCocktail(cocktail: Cocktail?, viewHolder: BarViewHolder) {
            itemName.text = cocktail?.name
            Picasso.get()
                .load(cocktail?.img)
                .fit()
                .centerCrop()
                .into(viewHolder.itemImg)
        }


        override fun onClick(v: View?) {
            itemClickListener?.onItemClick(adapterPosition, v)
        }
    }

}

