package fr.isen.panomix.ui.storage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import fr.isen.panomix.database.PanomixDatabase
import fr.isen.panomix.model.Ingredient
import fr.isen.panomix.repository.PanomixRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StorageViewModel(application: Application) : AndroidViewModel(application) {

    private val panomixDao =
        PanomixDatabase.getDatabase(application.applicationContext, viewModelScope).panomixDao()
    private val repository = PanomixRepository(panomixDao)
    val availableIngredients = repository.availableIngredients


    fun addIngredient(ingredient: Ingredient) = viewModelScope.launch(Dispatchers.IO) {
        repository.addIngredient(ingredient)
    }


}