package fr.isen.panomix.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.panomix.PanomixApplication
import fr.isen.panomix.R
import fr.isen.panomix.ui.adapter.CocktailAdapter
import fr.isen.panomix.ui.adapter.IngredientsAdapter
import fr.isen.panomix.ui.viewmodel.MainViewModel
import fr.isen.panomix.ui.viewmodel.MainViewModelFactory
import fr.isen.panomix.ui.viewmodel.RecipelViewModel
import fr.isen.panomix.ui.viewmodel.RecipelViewModelFactory
import kotlinx.android.synthetic.main.activity_ingredients_need.*
import kotlinx.coroutines.flow.toList


class ingredientsNeedActivity : AppCompatActivity() {
    private val recipelViewModel: RecipelViewModel by viewModels {
        RecipelViewModelFactory((this.application as PanomixApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = IngredientsAdapter()
        setContentView(R.layout.activity_ingredients_need)
        ingredientsList.layoutManager = LinearLayoutManager(this)
        val adapter = IngredientsAdapter()
        ingredientsList.adapter = adapter
        val id = intent.getIntExtra("itemId", 0)
        System.out.println(id)
        System.out.println(recipelViewModel)
        adapter.setItems(recipelViewModel.getCocktailIngredient(id))
    }

}