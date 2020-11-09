package fr.isen.panomix.api

import fr.isen.panomix.model.IngredientsAPI
import retrofit2.Call
import retrofit2.http.GET

interface IngredientsApiCall {
    @GET("list.php?i=list")
    fun getIngredients(): Call<List<IngredientsAPI>>
}