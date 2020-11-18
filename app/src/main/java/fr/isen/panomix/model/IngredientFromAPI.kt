package fr.isen.panomix.model

import com.google.gson.annotations.SerializedName

class IngredientFromAPI (
    @SerializedName("strIngredient1")
    val strIngredient: String?
)