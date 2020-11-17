package fr.isen.panomix.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import fr.isen.panomix.database.PanomixDao
import fr.isen.panomix.model.Cocktail
import fr.isen.panomix.model.Ingredient
import kotlinx.coroutines.flow.Flow

class PanomixRepository(private val panomixDao: PanomixDao) {

    val allIngredients: Flow<List<Ingredient>> = panomixDao.getAllIngredients()
    val allCocktail: Flow<List<Cocktail>> = panomixDao.getAllCocktails()
    val availableIngredients: Flow<List<Ingredient>> = panomixDao.getAvailableIngredients()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addCocktail(cocktail: Cocktail) {
        panomixDao.addCocktail(cocktail)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addIngredient(ingredient: Ingredient) {
        panomixDao.addIngredient(ingredient)
    }
}