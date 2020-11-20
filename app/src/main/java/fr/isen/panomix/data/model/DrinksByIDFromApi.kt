package fr.isen.panomix.data.model

import com.google.gson.annotations.SerializedName

class DrinksByIDFromApi(
    @SerializedName("idDrink")
    val id: String?,

    @SerializedName("strDrink")
    val drinkName: String?,

    @SerializedName("strInstructions")
    val strInstructions: String? = null,

    @SerializedName("strDrinkThumb")
    val img: String?,

    @SerializedName("strIngredient1")
    val strIngredient1: String?,

    @SerializedName("strIngredient2")
    val strIngredient2: String?,

    @SerializedName("strIngredient3")
    val strIngredient3: String?,

    @SerializedName("strIngredient4")
    val strIngredient4: String?,

    @SerializedName("strIngredient5")
    val strIngredient5: String?,

    @SerializedName("strMeasure1")
    val strQuantity1: String?,

    @SerializedName("strMeasure2")
    val strQuantity2: String?,

    @SerializedName("strMeasure3")
    val strQuantity3: String?,

    @SerializedName("strMeasure4")
    val strQuantity4: String?,

    @SerializedName("strMeasure5")
    val strQuantity5: String?
)