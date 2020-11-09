package fr.isen.panomix.api

import fr.isen.panomix.model.DrinksByIDAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinksByIDApiCall {
    @GET("lookup.php")
    fun getDrinks(@Query("i") drinkID: Int): Call<List<DrinksByIDAPI>>
}