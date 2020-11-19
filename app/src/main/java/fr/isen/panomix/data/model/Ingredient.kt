package fr.isen.panomix.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ingredient_table")
data class Ingredient(
    var name: String?,
    var unit: String?,
    var available: Boolean? = false,
    var img: String? = null,
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}