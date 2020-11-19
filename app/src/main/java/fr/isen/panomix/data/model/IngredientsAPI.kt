package fr.isen.panomix.data.model

import com.google.gson.annotations.SerializedName

class IngredientsAPI {
    @SerializedName("drinks")
    val strIngredients: List<IngredientFromAPI>? = ArrayList()
}