package com.jhonjto.co.zemogaapp.ui.comments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhonjto.co.zemogaapp.R

class ShowFavorites : Fragment() {

    companion object {
        fun newInstance() = ShowFavorites()
    }

    private lateinit var viewModel: ShowFavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.show_content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ShowFavoritesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}