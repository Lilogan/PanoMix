package fr.isen.panomix.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.isen.panomix.R

import fr.isen.panomix.data.api.DrinksByIDApiCall
import fr.isen.panomix.data.api.DrinksByIngredientApiCall
import fr.isen.panomix.data.api.IngredientsApiCall
import fr.isen.panomix.data.model.DrinksByIDAPI
import fr.isen.panomix.data.model.DrinksByIDFromApi
import fr.isen.panomix.data.model.DrinksByIngredientAPI
import fr.isen.panomix.data.model.IngredientsAPI

import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav?.setupWithNavController(navController)
    }
}