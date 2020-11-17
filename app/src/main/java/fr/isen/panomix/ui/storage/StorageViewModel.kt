package fr.isen.panomix.ui.storage


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import fr.isen.panomix.model.Ingredient
import fr.isen.panomix.repository.PanomixRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class StorageViewModel(private val repository: PanomixRepository) : ViewModel() {

    val availableIngredients = repository.availableIngredients.asLiveData()

    fun addIngredient(ingredient: Ingredient) = viewModelScope.launch() {
        repository.addIngredient(ingredient)
    }

}

class StorageViewModelFactory(private val repository: PanomixRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StorageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StorageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}
