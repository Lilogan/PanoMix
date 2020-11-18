package fr.isen.panomix.model

import fr.isen.panomix.model.IngredientFromAPI
import com.google.gson.annotations.SerializedName

class IngredientsAPI {
    @SerializedName("drinks")
    val strIngredients: List<IngredientFromAPI>? = ArrayList()
}