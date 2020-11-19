package fr.isen.panomix.data.api

import fr.isen.panomix.data.model.IngredientsAPI
import retrofit2.Call
import retrofit2.http.GET

interface IngredientsApiCall {
    @GET("list.php?i=list")
    fun getIngredients(): Call<IngredientsAPI>
}