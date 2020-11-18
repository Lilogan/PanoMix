package fr.isen.panomix.ui.bar


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import fr.isen.panomix.model.Cocktail
import fr.isen.panomix.model.Ingredient
import fr.isen.panomix.repository.PanomixRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BarViewModel(private val repository: PanomixRepository) : ViewModel() {

    val availableCocktails = repository.allCocktail.asLiveData()

    fun addCocktail(cocktail: Cocktail) = viewModelScope.launch() {
        repository.addCocktail(cocktail)
    }

}

class BarViewModelFactory(private val repository: PanomixRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BarViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}
