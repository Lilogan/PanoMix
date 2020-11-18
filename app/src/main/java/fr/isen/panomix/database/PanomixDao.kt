package fr.isen.panomix.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.isen.panomix.model.Cocktail
import fr.isen.panomix.model.Ingredient
import kotlinx.coroutines.flow.Flow

@Dao
interface PanomixDao {
    @Query("SELECT * FROM ingredient_table")
    fun getAllIngredients(): Flow<List<Ingredient>>

    @Query("SELECT * FROM ingredient_table WHERE quantity > 0")
    fun getAvailableIngredients(): Flow<List<Ingredient>>

    @Query("SELECT * FROM cocktail_table")
    fun getAllCocktails(): Flow<List<Cocktail>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIngredient(ingredient: Ingredient)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCocktail(cocktail: Cocktail)

    @Query("DELETE FROM ingredient_table")
    suspend fun deleteAllIngredient()

    @Query("DELETE FROM cocktail_table")
    suspend fun deleteAllCocktails()

}