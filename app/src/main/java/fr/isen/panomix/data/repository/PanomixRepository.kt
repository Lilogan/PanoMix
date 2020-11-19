package fr.isen.panomix.data.repository

import androidx.annotation.WorkerThread
import fr.isen.panomix.database.PanomixDao
import fr.isen.panomix.data.model.Cocktail
import fr.isen.panomix.data.model.Ingredient
import fr.isen.panomix.data.model.IngredientInCocktail
import kotlinx.coroutines.flow.Flow

class PanomixRepository(private val panomixDao: PanomixDao) {

    val allIngredients: Flow<List<Ingredient>> = panomixDao.getAllIngredients()
    val allCocktail: Flow<List<Cocktail>> = panomixDao.getAllCocktails()
    val availableIngredients: Flow<List<Ingredient>> = panomixDao.getAvailableIngredients()

    fun getCocktailByName(name: String): Cocktail {
        return panomixDao.getCocktailByName(name)
    }

    fun getIngredientByName(name: String): Ingredient {
        return panomixDao.getIngredientByName(name)
    }

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

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addIngredientInCocktail(ingredientInCocktail: IngredientInCocktail) {
        panomixDao.addIngredientInCocktail(ingredientInCocktail)
    }


}