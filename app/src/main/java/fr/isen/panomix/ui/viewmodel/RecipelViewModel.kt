package fr.isen.panomix.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import fr.isen.panomix.data.model.Cocktail
import fr.isen.panomix.data.model.Ingredient
import fr.isen.panomix.data.repository.PanomixRepository
import java.lang.IllegalArgumentException

class RecipelViewModel(private val repository: PanomixRepository) : ViewModel()  {
    //TODO: remove
    val allIngredients = repository.allIngredients.asLiveData()
    val allCocktails = repository.allCocktail.asLiveData()

    /*
    fun getCocktailIngredient(id: Int): MutableList<Ingredient> {
        val ingredients = mutableListOf<Ingredient>()
        if (repository.getCocktailIngredient(id).asLiveData().value != null){
            for (ingredient in repository.getCocktailIngredient(id).asLiveData().value!!) {
                val ingredientById = ingredient.id?.let { repository.getIngredientById(it).asLiveData().value }
                if(ingredientById != null){
                    ingredients.add(ingredientById)
                }
            }
        }
        return ingredients
    }

     */

    fun getCocktailIngredient(cocktailId : Int): MutableList<Ingredient> {
        val allCocktails = allCocktails.value
        val allIngredients = allIngredients.value
        var ingredientsNeed = mutableListOf<Ingredient>();
        if (allCocktails != null && allIngredients != null) {
            for (cocktail in allCocktails) {
                val currentCocktailId = cocktail.id
                if (cocktailId == currentCocktailId)
                {
                    val cocktailIngredient = cocktailId?.let { repository.getIngredientsFromCocktail(it).asLiveData().value }
                    System.out.println("Cocktail Ingredient : " + cocktailIngredient)
                    if (cocktailIngredient != null) {
                        for (ingredient in cocktailIngredient) {
                            ingredientsNeed.add(ingredient)
                        }
                    }
                }
            }
        }
        return ingredientsNeed
    }

}


class RecipelViewModelFactory(private val repository: PanomixRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipelViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipelViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}