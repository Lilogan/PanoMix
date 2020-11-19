package fr.isen.panomix.data.api

import fr.isen.panomix.data.model.DrinksByIDAPI
import fr.isen.panomix.data.model.DrinksByIngredientAPI
import fr.isen.panomix.data.model.IngredientsAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("lookup.php")
    fun getDrinks(@Query("i") drinkID: String): Call<DrinksByIDAPI>

    @GET("list.php?i=list")
    fun getIngredients(): Call<IngredientsAPI>

    @GET("filter.php")
    fun getDrinksByIngredient(@Query("i") ingredient: String): Call<DrinksByIngredientAPI>

}