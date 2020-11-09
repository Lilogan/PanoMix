package fr.isen.panomix.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_table")
data class Ingredient(
    var name: String?,
    var unit: String?,
    var quantity: Double?,
    var img: String? = null,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}