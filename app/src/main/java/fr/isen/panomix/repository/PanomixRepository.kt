package fr.isen.panomix.repository

import androidx.lifecycle.LiveData
import fr.isen.panomix.database.PanomixDao
import fr.isen.panomix.model.Cocktail
import fr.isen.panomix.model.Ingredient

class PanomixRepository(private val panomixDao: PanomixDao) {

    val allIngredients: LiveData<List<Ingredient>> = panomixDao.getAllIngredients()
    val allCocktail: LiveData<List<Cocktail>> = panomixDao.getAllCocktails()
    val availableIngredients: LiveData<List<Ingredient>> = panomixDao.getAvailableIngredients()

    suspend fun addCocktail(cocktail: Cocktail) {
        panomixDao.addCocktail(cocktail)
    }

    suspend fun addIngredient(ingredient: Ingredient) {
        panomixDao.addIngredient(ingredient)
    }
}