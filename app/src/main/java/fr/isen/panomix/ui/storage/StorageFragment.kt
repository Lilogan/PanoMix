package fr.isen.panomix.ui.storage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.panomix.R
import fr.isen.panomix.adapter.IngredientsAdapter

/**
 * A simple [Fragment] subclass.
 */
class StorageFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var storageViewModel: StorageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_storage, container, false)
        recyclerView = view.findViewById(R.id.storage_recycler_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = IngredientsAdapter(view.context)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        storageViewModel = ViewModelProvider(this).get(StorageViewModel::class.java)
        storageViewModel.availableIngredients.observe(
            viewLifecycleOwner,
            { ingredients -> ingredients?.let { adapter.setIngredients(it) } })
    }

}