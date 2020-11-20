package fr.isen.panomix.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import fr.isen.panomix.data.api.ApiService
import fr.isen.panomix.data.model.Cocktail
import fr.isen.panomix.data.model.Ingredient
import fr.isen.panomix.data.model.IngredientInCocktail
import fr.isen.panomix.data.repository.PanomixRepository
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalArgumentException

class SplashViewModel(private val repository: PanomixRepository) : ViewModel() {

    fun getIngredientByName(name: String) = repository.getIngredientByName(name).asLiveData()
    fun getCocktailByName(name: String) = repository.getCocktailByName(name).asLiveData()

    fun addIngredient(ingredient: Ingredient) = viewModelScope.launch() {
        repository.addIngredient(ingredient)
    }

    fun addCocktail(cocktail: Cocktail) = viewModelScope.launch() {
        repository.addCocktail(cocktail)
    }

    fun addIngredientInCocktail(ingredientInCocktail: IngredientInCocktail) =
        viewModelScope.launch {
            repository.addIngredientInCocktail(ingredientInCocktail)
        }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(ApiService::class.java)

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