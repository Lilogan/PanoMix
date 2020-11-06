package fr.isen.panomix.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "map_cocktail_ingredient")
data class Cocktail_Ingredient(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var id_cocktail: Int,
    var id_ingredient: Int,
    var quantity: Float,

    )