package fr.isen.panomix.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktail_table")
data class Cocktail(
    var name: String?,
    var img: String? = null,
    var instruction: String? = null,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}