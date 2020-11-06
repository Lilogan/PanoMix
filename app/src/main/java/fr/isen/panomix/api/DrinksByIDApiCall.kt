package fr.isen.panomix.api

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import fr.isen.panomix.model.DrinksByIDAPI

interface DrinksByIDApiCall {
    @GET("lookup.php")
    fun getDrinks(@Query("i") drinkID: Int): Call<List<DrinksByIDAPI>>
}