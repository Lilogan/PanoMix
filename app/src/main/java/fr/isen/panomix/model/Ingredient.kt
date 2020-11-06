package fr.isen.panomix.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_table")
data class Ingredient(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    var unit: String,
    var quantity: Float,
    var img: String,
)