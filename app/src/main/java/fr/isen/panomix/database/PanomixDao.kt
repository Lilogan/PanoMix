package fr.isen.panomix.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.isen.panomix.model.Cocktail
import fr.isen.panomix.model.Ingredient

@Dao
interface PanomixDao {
    @Query("SELECT * FROM ingredient_table")
    fun getAllIngredients(): LiveData<List<Ingredient>>

    @Query("SELECT * FROM ingredient_table WHERE quantity > 0")
    fun getAvailableIngredients(): LiveData<List<Ingredient>>

    @Query("SELECT * FROM cocktail_table")
    fun getAllCocktails(): LiveData<List<Cocktail>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIngredient(ingredient: Ingredient)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCocktail(cocktail: Cocktail)

    @Query("DELETE FROM ingredient_table")
    suspend fun deleteAllIngredient()

}