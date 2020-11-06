package fr.isen.panomix.dao

import androidx.room.Dao
import androidx.room.Query
import fr.isen.panomix.model.Cocktail
import fr.isen.panomix.model.Ingredient

@Dao
interface PanomixDao {
    @Query("SELECT * FROM ingredient_table")
    fun getAllIngredients(): List<Ingredient>

    @Query("SELECT * FROM cocktail_table")
    fun getAllCocktails(): List<Cocktail>

}