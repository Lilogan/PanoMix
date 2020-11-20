package fr.isen.panomix.data.repository

import androidx.annotation.WorkerThread
import fr.isen.panomix.data.api.ApiService
import fr.isen.panomix.database.PanomixDao
import fr.isen.panomix.data.model.Cocktail
import fr.isen.panomix.data.model.Ingredient
import fr.isen.panomix.data.model.IngredientInCocktail
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PanomixRepository(private val panomixDao: PanomixDao) {

    val allIngredients: Flow<List<Ingredient>> = panomixDao.getAllIngredients()
    val allCocktail: Flow<List<Cocktail>> = panomixDao.getAllCocktails()
    val availableIngredients: Flow<List<Ingredient>> = panomixDao.getAvailableIngredients()


    fun getCocktailByName(name: String): Flow<Cocktail> {
        return panomixDao.getCocktailByName(name)
    }

    fun getCocktailById(id: Int): Flow<Cocktail> {
        return panomixDao.getCocktailById(id)
    }

    fun getIngredientByName(name: String): Flow<Ingredient> {
        return panomixDao.getIngredientByName(name)
    }

    fun getIngredientById(id: Int): Flow<Ingredient> {
        return panomixDao.getIngredientById(id)
    }

    fun getCocktailIngredient(id: Int): Flow<List<IngredientInCocktail>> {
        return panomixDao.getIngredientInCocktail(id)
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
    suspend fun updateIngredient(id: Int, available: Boolean) {
        panomixDao.updateIngredient(id, available)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addIngredientInCocktail(ingredientInCocktail: IngredientInCocktail) {
        panomixDao.addIngredientInCocktail(ingredientInCocktail)
    }

}