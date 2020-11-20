package fr.isen.panomix.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.isen.panomix.data.model.Cocktail
import fr.isen.panomix.data.model.Ingredient
import fr.isen.panomix.data.model.IngredientInCocktail
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
                    panomixDao.deleteAllIngredientInCocktail()
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
}

suspend fun populateCocktails(dao: PanomixDao) {
    dao.deleteAllCocktails()

}
