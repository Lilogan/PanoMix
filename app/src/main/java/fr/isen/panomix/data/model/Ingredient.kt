package fr.isen.panomix.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ingredient_table")
data class Ingredient(
    var id: Int?,
    var name: String?,
    var available: Boolean? = false,
    var img: String? = null,
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var element_id: Int? = null
}