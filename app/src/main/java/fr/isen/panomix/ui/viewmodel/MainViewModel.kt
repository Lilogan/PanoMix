package fr.isen.panomix.ui.viewmodel

import androidx.lifecycle.*
import fr.isen.panomix.data.model.Cocktail
import fr.isen.panomix.data.model.Ingredient
import fr.isen.panomix.data.repository.PanomixRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(private val repository: PanomixRepository) : ViewModel() {

    val availableIngredients = repository.availableIngredients.asLiveData()
    val allIngredients = repository.allIngredients.asLiveData()
    val allCocktails = repository.allCocktail.asLiveData()


    fun getPossibleCocktails(): MutableList<Cocktail> {
        val allCocktails = allCocktails.value
        val allIngredients = availableIngredients.value
        var possibleCocktails = mutableListOf<Cocktail>();
//        if (allCocktails != null && allIngredients != null) {
//            for (cocktail in allCocktails) {
//                val cocktailId = cocktail.id
//                val cocktailIngredient =
//                    cocktailId?.let { repository.getCocktailIngredient(it).asLiveData().value }
//                var allIngredientAvailable = true;
//                if (cocktailIngredient != null) {
//                    for (ingredient in cocktailIngredient) {
//                        if (!(ingredient in allIngredients)) {
//                            allIngredientAvailable = false
//                            break
//                        }
//                    }
//                    if (allIngredientAvailable) {
//                        possibleCocktails.add(cocktail)
//                    }
//                }
//
//            }
//        }
        return possibleCocktails

    }

    fun getCocktailIngredients(cocktailId : Int): MutableList<Ingredient>{
        var cocktailIngredients = mutableListOf<Ingredient>()

        return cocktailIngredients
    }

    fun addIngredient(ingredient: Ingredient) = viewModelScope.launch() {
        repository.addIngredient(ingredient)
    }

    fun addCocktail(cocktail: Cocktail) = viewModelScope.launch() {
        repository.addCocktail(cocktail)
    }

}

class MainViewModelFactory(private val repository: PanomixRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}