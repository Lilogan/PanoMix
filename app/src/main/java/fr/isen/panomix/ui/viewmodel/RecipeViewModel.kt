package fr.isen.panomix.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import fr.isen.panomix.data.model.Ingredient
import fr.isen.panomix.data.repository.PanomixRepository
import java.lang.IllegalArgumentException
import java.util.concurrent.Flow

class RecipeViewModel(private val repository: PanomixRepository) : ViewModel() {
    fun getCocktailIngredient(id: Int): MutableList<Ingredient> {
        val ingredients = mutableListOf<Ingredient>()
        if (repository.getCocktailIngredient(id).asLiveData().value != null) {
            for (ingredient in repository.getCocktailIngredient(id).asLiveData().value!!) {
                val ingredientById =
                    ingredient.id?.let { repository.getIngredientById(it).asLiveData().value }
                if (ingredientById != null) {
                    ingredients.add(ingredientById)
                }
            }
        }

        return ingredients
    }

    fun ingredientFromCocktail(id: Int) = repository.getIngredientsFromCocktail(id).asLiveData()

}


class RecipeViewModelFactory(private val repository: PanomixRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}