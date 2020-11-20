package fr.isen.panomix.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import fr.isen.panomix.R
import fr.isen.panomix.data.model.Ingredient
import fr.isen.panomix.ui.viewmodel.MainViewModelFactory

class NewIngredientActivity : AppCompatActivity() {
    private lateinit var editName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_ingredient)

        editName = findViewById(R.id.editNewIngredientName)
        val button = findViewById<Button>(R.id.submitNewIngredientButton)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editName.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val name = editName.text.toString()
                replyIntent.putExtra("new_ingredient", Ingredient(null, name))
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}