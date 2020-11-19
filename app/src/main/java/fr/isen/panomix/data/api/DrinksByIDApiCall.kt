package fr.isen.panomix.data.api

import fr.isen.panomix.data.model.DrinksByIDAPI
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface DrinksByIDApiCall {
    @GET("lookup.php")
    fun getDrinks(@Query("i") drinkID: String): Call<DrinksByIDAPI>
}