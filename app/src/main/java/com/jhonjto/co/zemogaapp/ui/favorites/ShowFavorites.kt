package com.jhonjto.co.zemogaapp.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhonjto.co.zemogaapp.R
import com.jhonjto.co.zemogaapp.adapters.FavoritesAdapter
import com.jhonjto.co.zemogaapp.ui.favorites.ShowFavoritesViewModel.UiModel
import kotlinx.android.synthetic.main.show_content_fragment.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class ShowFavorites : Fragment() {

    private lateinit var adapter: FavoritesAdapter
    private val viewModel: ShowFavoritesViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.show_content_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        adapter = FavoritesAdapter()
        favorites_recycler_view.layoutManager = LinearLayoutManager(context)
        favorites_recycler_view.adapter = adapter

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun updateUi(model: UiModel) {
        progressFavorites.visibility = if (model == UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is UiModel.Content -> adapter.favorites = model.posts
        }
    }
}