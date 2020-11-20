package fr.isen.panomix.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.viewModels
import fr.isen.panomix.PanomixApplication
import fr.isen.panomix.R
import fr.isen.panomix.data.api.ApiService
import fr.isen.panomix.data.api.DrinksByIDApiCall
import fr.isen.panomix.data.api.DrinksByIngredientApiCall
import fr.isen.panomix.data.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import fr.isen.panomix.ui.viewmodel.SplashViewModel
import fr.isen.panomix.ui.viewmodel.SplashViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SplashActivity : AppCompatActivity() {

    private val context = this

    private val viewModel: SplashViewModel by viewModels {
        SplashViewModelFactory((application as PanomixApplication).repository)
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(ApiService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Launcher)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sheduleTest()

        val ingredientsList = mutableListOf<String>()
        val ingredientsRequest = service.getIngredients()

        ingredientsRequest.enqueue(object : Callback<IngredientsAPI> {
            override fun onResponse(
                call: Call<IngredientsAPI>,
                response: Response<IngredientsAPI>
            ) {
                for (x in 0 until (response.body()?.strIngredients?.size!!)) {
                    response.body()?.strIngredients?.get(x)?.strIngredient?.let {
                        ingredientsList.add(
                            it
                        )
                    }
                }

                for (element in ingredientsList) {
                    val currentIngredient = Ingredient(ingredientsList.indexOf(element), element)
                    viewModel.addIngredient(currentIngredient)
                    getDrinksListByIngredients(currentIngredient)
                }
            }

            override fun onFailure(call: Call<IngredientsAPI>, t: Throwable) {
                t.message?.let { error(it) }
            }
        })


    }

    private fun getDrinksListByIngredients(ingredient: Ingredient?) {

        val allDrinks = mutableListOf<Int>()

        val service = retrofit.create(DrinksByIngredientApiCall::class.java)


        val drinksListRequest = ingredient?.name?.let { service.getDrinksByIngredient(it) }
        drinksListRequest?.enqueue(object : Callback<DrinksByIngredientAPI> {
            override fun onResponse(
                call: Call<DrinksByIngredientAPI>,
                response: Response<DrinksByIngredientAPI>
            ) {
                for (x in 0 until response.body()?.drinks?.size!!) {
                    response.body()?.drinks?.get(x)?.drinkID?.let { allDrinks.add(it) }
                }

                getAllDrinksByGivenID(allDrinks, ingredient)
                allDrinks.clear()

            }

            override fun onFailure(call: Call<DrinksByIngredientAPI>, t: Throwable) {
                error("Error while calling API")
            }


        })

    }

    private fun getAllDrinksByGivenID(idList: MutableList<Int>, ingredient: Ingredient) {
        val service = retrofit.create(DrinksByIDApiCall::class.java)

        val allDrinksByID = mutableListOf<DrinksByIDFromApi>()

        for (id in idList) {
            val drinksByIDRequest = service.getDrinks(id.toString())
            drinksByIDRequest.enqueue(object : Callback<DrinksByIDAPI> {
                override fun onResponse(
                    call: Call<DrinksByIDAPI>,
                    response: Response<DrinksByIDAPI>
                ) {
                    for (x in 0 until response.body()?.drinks?.size!!) {
                        response.body()?.drinks?.get(x)?.let { allDrinksByID.add(it) }
                    }


                    for (element in allDrinksByID) {
                        val currentCocktail =
                            Cocktail(
                                element.id?.toInt(),
                                element.drinkName,
                                element.img,
                                element.strInstructions
                            )
                        viewModel.addCocktail(currentCocktail)
                        if(mutableListOf<String?>(element.strIngredient1, element.strIngredient2, element.strIngredient3, element.strIngredient4, element.strIngredient5).contains(ingredient.name)){
                            val ingredient1Q = element.strQuantity1

                            val linkIngredientCocktail = IngredientInCocktail(
                                element.id?.toInt(),
                                ingredient.id,
                                ingredient1Q
                            )
                            viewModel.addIngredientInCocktail(linkIngredientCocktail)
                        }

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

    private fun sheduleTest() {
        val testDuration = 2000L
        Handler().postDelayed({

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, testDuration)
    }
}