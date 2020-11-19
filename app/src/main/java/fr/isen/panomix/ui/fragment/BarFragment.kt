package fr.isen.panomix.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.panomix.PanomixApplication
import fr.isen.panomix.R
import fr.isen.panomix.ui.adapter.BarAdapter
import fr.isen.panomix.ui.adapter.HeaderViewDecoration
import fr.isen.panomix.ui.viewmodel.MainViewModel
import fr.isen.panomix.ui.viewmodel.MainViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class BarFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModelFactory((activity?.application as PanomixApplication).repository)
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

        val adapter = BarAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.cocktailRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        val headerView =
            LayoutInflater.from(recyclerView.context).inflate(R.layout.header_bar, null)
        recyclerView.addItemDecoration(HeaderViewDecoration(headerView))

        mainViewModel.availableCocktails.observe(
            viewLifecycleOwner,
            { cocktails -> cocktails.let { adapter.setItems(it) } })
    }

}