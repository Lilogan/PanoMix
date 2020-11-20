package fr.isen.panomix.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.asLiveData
import fr.isen.panomix.data.api.ApiService
import fr.isen.panomix.database.PanomixDao
import fr.isen.panomix.data.model.Cocktail
import fr.isen.panomix.data.model.Ingredient
import fr.isen.panomix.data.model.IngredientInCocktail
import fr.isen.panomix.data.model.IngredientsAPI
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PanomixRepository(private val panomixDao: PanomixDao) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(ApiService::class.java)

    val allIngredients: Flow<List<Ingredient>> = panomixDao.getAllIngredients()
    val allCocktail: Flow<List<Cocktail>> = panomixDao.getAllCocktails()
    val unavailableIngredient: Flow<List<Ingredient>> = panomixDao.getAvailableIngredients(false)
    val availableIngredients: Flow<List<Ingredient>> = panomixDao.getAvailableIngredients(true)


    fun getCocktailByName(name: String): Flow<Cocktail> {
        return panomixDao.getCocktailByName(name)
    }

    fun getIngredientByName(name: String): Flow<Ingredient> {
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


    fun getCocktailIngredient(id: Int): Flow<List<Ingredient>> {
        return panomixDao.getCocktailIngredients(id)
    }

    fun getIngredientById(id: Int): Flow<Ingredient> {
        return panomixDao.getIngredientById(id)
    }



    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addIngredientInCocktail(ingredientInCocktail: IngredientInCocktail) {
        panomixDao.addIngredientInCocktail(ingredientInCocktail)
    }

    @WorkerThread
    suspend fun deleteOneIngredient(ingredient : Ingredient){
        panomixDao.deleteOneIngredient(ingredient)
    }
}