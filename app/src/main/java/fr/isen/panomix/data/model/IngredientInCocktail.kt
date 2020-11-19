package fr.isen.panomix.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "map_cocktail_ingredient")
data class IngredientInCocktail(
    var id_cocktail: Int?,
    var id_ingredient: Int?,
    var quantity: Double?,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}