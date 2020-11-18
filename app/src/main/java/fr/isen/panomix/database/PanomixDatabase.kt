package fr.isen.panomix.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.isen.panomix.model.Cocktail
import fr.isen.panomix.model.Ingredient
import fr.isen.panomix.model.IngredientInCocktail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Cocktail::class, Ingredient::class, IngredientInCocktail::class], version = 1)
abstract class PanomixDatabase : RoomDatabase() {
    abstract fun panomixDao(): PanomixDao

    private class PanomixDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val panomixDao = database.panomixDao()
                    populateIngredients(panomixDao)
                    populateCocktails(panomixDao)
                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: PanomixDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PanomixDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PanomixDatabase::class.java,
                    "panomix.db"
                ).addCallback(PanomixDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

suspend fun populateIngredients(dao: PanomixDao) {
    dao.deleteAllIngredient()
    dao.addIngredient(Ingredient("Water", "L", 2.0))
    dao.addIngredient(Ingredient("Wine", "cl", 50.0))
    dao.addIngredient(Ingredient("Soda", "L", 5.0))
    dao.addIngredient(Ingredient("Vodka", "cl", 30.0))
    dao.addIngredient(Ingredient("Rhum", "cl", 40.0))
}

suspend fun populateCocktails(dao: PanomixDao) {
    dao.deleteAllCocktails()
    dao.addCocktail(Cocktail("Margarita", "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg"))
    dao.addCocktail(Cocktail("69 Special", "https://www.thecocktaildb.com/images/media/drink/vqyxqx1472669095.jpg"))
    dao.addCocktail(Cocktail("Adam & Eve", "https://www.thecocktaildb.com/images/media/drink/vfeumw1504819077.jpg"))
    dao.addCocktail(Cocktail("Alaska Cocktail", "https://www.thecocktaildb.com/images/media/drink/wsyryt1483387720.jpg"))
    dao.addCocktail(Cocktail("Bijou", "https://www.thecocktaildb.com/images/media/drink/rysb3r1513706985.jpg"))
}
