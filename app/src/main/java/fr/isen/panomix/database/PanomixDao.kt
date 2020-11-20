package fr.isen.panomix.database

import androidx.room.*
import fr.isen.panomix.data.model.Cocktail
import fr.isen.panomix.data.model.Ingredient
import fr.isen.panomix.data.model.IngredientInCocktail
import kotlinx.coroutines.flow.Flow

@Dao
interface PanomixDao {
    @Query("SELECT * FROM ingredient_table")
    fun getAllIngredients(): Flow<List<Ingredient>>

    @Query("SELECT * FROM ingredient_table WHERE available")
    fun getAvailableIngredients(): Flow<List<Ingredient>>

    @Query("SELECT * FROM cocktail_table")
    fun getAllCocktails(): Flow<List<Cocktail>>

    @Query("SELECT * FROM cocktail_table WHERE name = :name LIMIT 1")
    fun getCocktailByName(name: String): Flow<Cocktail>

    @Query("SELECT * FROM ingredient_table WHERE name = :name LIMIT 1")
    fun getIngredientByName(name: String): Flow<Ingredient>

    @Query("SELECT * FROM ingredient_table WHERE id = :id LIMIT 1")
    fun getIngredientById(id: Int): Flow<Ingredient>

    @Query("SELECT * FROM cocktail_table WHERE id == :id LIMIT 1")
    fun getCocktailById(id: Int): Flow<Cocktail>

    @Query("SELECT * FROM map_cocktail_ingredient WHERE id_cocktail == :id_cocktail")
    fun getIngredientInCocktail(id_cocktail: Int): Flow<List<IngredientInCocktail>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIngredient(ingredient: Ingredient)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCocktail(cocktail: Cocktail)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIngredientInCocktail(ingredientInCocktail: IngredientInCocktail)

    @Query("UPDATE ingredient_table SET available = :available WHERE id = :id")
    suspend fun updateIngredient(id: Int, available: Boolean)

    @Query("DELETE FROM ingredient_table")
    suspend fun deleteAllIngredient()

    @Query("DELETE FROM cocktail_table")
    suspend fun deleteAllCocktails()

    @Query("DELETE FROM map_cocktail_ingredient")
    suspend fun deleteAllIngredientInCocktail()

    @Update
    suspend fun updateItem(ingredient: Ingredient)

    @Delete
    suspend fun deleteOneIngredient(ingredient: Ingredient)

}