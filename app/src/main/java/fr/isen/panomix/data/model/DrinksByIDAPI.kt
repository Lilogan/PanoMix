package fr.isen.panomix.data.model

import com.google.gson.annotations.SerializedName

class DrinksByIDAPI (
    @SerializedName("drinks")
    val drinks: List<DrinksByIDFromApi>
)