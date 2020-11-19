package fr.isen.panomix.data.repository

import androidx.annotation.WorkerThread
import fr.isen.panomix.database.PanomixDao
import fr.isen.panomix.data.model.Cocktail
import fr.isen.panomix.data.model.Ingredient
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

    @WorkerThread
    suspend fun deleteOneIngredient(ingredient : Ingredient){
        panomixDao.deleteOneIngredient(ingredient)
    }
}