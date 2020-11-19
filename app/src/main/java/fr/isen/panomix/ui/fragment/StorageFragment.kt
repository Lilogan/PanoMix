package fr.isen.panomix.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.isen.panomix.ui.activity.NewIngredientActivity
import fr.isen.panomix.PanomixApplication
import fr.isen.panomix.R
import fr.isen.panomix.ui.adapter.HeaderViewDecoration
import fr.isen.panomix.ui.adapter.IngredientsAdapter
import fr.isen.panomix.data.model.Ingredient
import fr.isen.panomix.ui.viewmodel.MainViewModel
import fr.isen.panomix.ui.viewmodel.MainViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class StorageFragment : Fragment() {

    private val newIngredientRequestCode = 1

    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModelFactory((activity?.application as PanomixApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_storage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = IngredientsAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.storageRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(view.context, 3)

        val headerView =
            LayoutInflater.from(recyclerView.context).inflate(R.layout.header_storage, null)
        recyclerView.addItemDecoration(HeaderViewDecoration(headerView))

        val button = view.findViewById<FloatingActionButton>(R.id.addNewIngredientButton)
        button.setOnClickListener {
            val intent = Intent(it.context, NewIngredientActivity::class.java)
            startActivityForResult(intent, newIngredientRequestCode)
        }

        mainViewModel.availableIngredients.observe(
            viewLifecycleOwner,
            { ingredients -> ingredients.let { adapter.setItems(it) } })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newIngredientRequestCode && resultCode == Activity.RESULT_OK) {
            val newIngredient = data?.getSerializableExtra("new_ingredient") as? Ingredient
            if (newIngredient != null) {
                mainViewModel.addIngredient(newIngredient)
                Toast.makeText(context, newIngredient.name + " added !", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
        }
    }
}