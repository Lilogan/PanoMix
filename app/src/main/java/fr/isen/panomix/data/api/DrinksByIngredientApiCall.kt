package fr.isen.panomix.data.api

import fr.isen.panomix.data.model.DrinksByIngredientAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinksByIngredientApiCall {
    @GET("filter.php")
    fun getDrinksByIngredient(@Query("i") ingredient: String): Call<DrinksByIngredientAPI>
}