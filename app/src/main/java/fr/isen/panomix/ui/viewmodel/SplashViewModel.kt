package fr.isen.panomix.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import fr.isen.panomix.data.model.Cocktail
import fr.isen.panomix.data.model.Ingredient
import fr.isen.panomix.data.repository.PanomixRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SplashViewModel(private val repository: PanomixRepository) : ViewModel() {

    val availableIngredients = repository.availableIngredients.asLiveData()
    val availableCocktails = repository.allCocktail.asLiveData()

    fun addIngredient(ingredient: Ingredient) = viewModelScope.launch() {
        repository.addIngredient(ingredient)
    }

    fun addCocktail(cocktail: Cocktail) = viewModelScope.launch() {
        repository.addCocktail(cocktail)
    }

}

class SplashViewModelFactory(private val repository: PanomixRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SplashViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}