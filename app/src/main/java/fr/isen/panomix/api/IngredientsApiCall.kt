package fr.isen.panomix.api

import retrofit2.Call;
import retrofit2.http.GET;

import fr.isen.panomix.model.IngredientsAPI

interface IngredientsApiCall {
    @GET("list.php?i=list")
    fun getIngredients(): Call<List<IngredientsAPI>>
}