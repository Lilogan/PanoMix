package fr.isen.panomix.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.panomix.PanomixApplication
import fr.isen.panomix.R
import fr.isen.panomix.ui.adapter.RecipeAdapter
import fr.isen.panomix.ui.viewmodel.*


class RecipeActivity : AppCompatActivity() {

    private val viewModel: RecipeViewModel by viewModels {
        RecipeViewModelFactory((this.application as PanomixApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val adapter = RecipeAdapter()

        val recyclerView = findViewById<RecyclerView>(R.id.recipeRecylcerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val id = intent.getIntExtra("itemId", 0)
        Log.d("Recipe", id.toString())
//        viewModel.ingredientFromCocktail(id).observe(this, { ingredients ->
//            ingredients.let { adapter.setItems(it) }
//        })


    }

}