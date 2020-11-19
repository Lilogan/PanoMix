package fr.isen.panomix.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import fr.isen.panomix.R
import fr.isen.panomix.data.model.Ingredient

class NewIngredientActivity : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editQuantity: EditText
    private lateinit var unitRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_ingredient)

        editName = findViewById(R.id.editNewIngredientName)
        editQuantity = findViewById(R.id.editNewIngredientQuantity)
        unitRadioGroup = findViewById(R.id.newIngredientUnitRadioGroup)

        val button = findViewById<Button>(R.id.submitNewIngredientButton)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editName.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val name = editName.text.toString()
                replyIntent.putExtra("new_ingredient", Ingredient(name))
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}