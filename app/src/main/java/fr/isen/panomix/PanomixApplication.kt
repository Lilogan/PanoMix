package fr.isen.panomix

import android.app.Application
import fr.isen.panomix.database.PanomixDatabase
import fr.isen.panomix.data.repository.PanomixRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PanomixApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { PanomixDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { PanomixRepository(database.panomixDao()) }
}