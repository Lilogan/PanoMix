package fr.isen.panomix.model

import com.google.gson.annotations.SerializedName

class DrinksByIngredientFromApi (
    @SerializedName("strDrink")
    val drinkName: String?,
    @SerializedName("idDrink")
    val drinkID: Int?
)