package fr.isen.panomix.api

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import fr.isen.panomix.model.DrinksByIngredientAPI;

interface DrinksByIngredientApiCall {
    @GET("filter.php")
    fun getDrinksByIngredient(@Query("i") ingredient: String): Call<List<DrinksByIngredientAPI>>
}