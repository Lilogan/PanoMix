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

    private val allDrinks = mutableListOf<Int>()

    private val ingredientsList = mutableListOf<String>()

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
        getIngredients()
    }

    private fun getIngredients(){
        val service = retrofit.create(IngredientsApiCall::class.java)

        val ingredientsRequest = service.getIngredients()

        ingredientsRequest.enqueue(object: Callback<IngredientsAPI> {
            override fun onResponse(
                call: Call<IngredientsAPI>,
                response: Response<IngredientsAPI>
            ) {
                for (x in 0 until (response.body()?.strIngredients?.size!!)){
                    response.body()?.strIngredients?.get(x)?.strIngredient?.let {
                        ingredientsList.add(
                            it
                        )
                    }
                }
                getDrinksListByIngredients(ingredientsList)
            }

            override fun onFailure(call: Call<IngredientsAPI>, t: Throwable) {
                t.message?.let { error(it) }
            }
        })
    }

    private fun getDrinksListByIngredients(ingredients: List<String>?){

        val service = retrofit.create(DrinksByIngredientApiCall::class.java)

        if (ingredients != null) {
            for (ingredient in ingredients){
                val drinksListRequest = service.getDrinksByIngredient(ingredient.toString())
                drinksListRequest.enqueue(object: Callback<DrinksByIngredientAPI> {
                    override fun onResponse(
                        call: Call<DrinksByIngredientAPI>,
                        response: Response<DrinksByIngredientAPI>
                    ) {
                        for (x in 0 until response.body()?.drinks?.size!!){
                            response.body()?.drinks?.get(x)?.drinkID?.let { allDrinks.add(it) }
                        }

                        getAllDrinksByGivenID(allDrinks)
                        allDrinks.clear()
                    }

                    override fun onFailure(call: Call<DrinksByIngredientAPI>, t: Throwable) {
                        error("Error while calling API")
                    }

                })
            }
        }
        else{
            print("Empty ingredients list...")
        }

    }

    private fun getAllDrinksByGivenID(idList: MutableList<Int>){
        val service = retrofit.create(DrinksByIDApiCall::class.java)

        val allDrinksByID = mutableListOf<DrinksByIDFromApi>()

        if(idList != null){
            for (id in idList){
                val drinksByIDRequest = service.getDrinks(id.toString())
                drinksByIDRequest.enqueue(object : Callback<DrinksByIDAPI> {
                    override fun onResponse(
                        call: Call<DrinksByIDAPI>,
                        response: Response<DrinksByIDAPI>
                    ) {
                        for (x in 0 until response.body()?.drinks?.size!!) {
                            response.body()?.drinks?.get(x)?.let { allDrinksByID.add(it) }
                        }

                        allDrinksByID.clear()
                        Log.d("Api", "Calls End")
                    }

                    override fun onFailure(call: Call<DrinksByIDAPI>, t: Throwable) {
                        error("Error while calling API getAllDrinksByGivenID")
                    }
                })
            }
        }
    }
}