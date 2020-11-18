package fr.isen.panomix.ui.bar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.panomix.PanomixApplication
import fr.isen.panomix.R
import fr.isen.panomix.adapter.BarAdapter

/**
 * A simple [Fragment] subclass.
 */
class BarFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: BarAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val barViewModel: BarViewModel by viewModels {
        BarViewModelFactory((activity?.application as PanomixApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(view.context)
        viewAdapter = BarAdapter()
        recyclerView = view.findViewById(R.id.cocktailList)
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = viewAdapter

        barViewModel.availableCocktails.observe(
            viewLifecycleOwner,
            { cocktails -> cocktails?.let { viewAdapter.setItems(it) } })
    }

}