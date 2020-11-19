package fr.isen.panomix.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.panomix.PanomixApplication
import fr.isen.panomix.R
import fr.isen.panomix.ui.adapter.IngredientsAdapter
import fr.isen.panomix.ui.viewmodel.MainViewModel
import fr.isen.panomix.ui.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_ingredients_need.*


class ingredientsNeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients_need)
        ingredientsList.layoutManager = LinearLayoutManager(this)
        ingredientsList.adapter = IngredientsAdapter()
    }

}