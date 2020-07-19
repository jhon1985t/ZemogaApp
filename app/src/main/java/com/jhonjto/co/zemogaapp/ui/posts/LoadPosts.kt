package com.jhonjto.co.zemogaapp.ui.posts

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhonjto.co.zemogaapp.R

class LoadPosts : Fragment() {

    companion object {
        fun newInstance() = LoadPosts()
    }

    private lateinit var viewModel: LoadPostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.load_posts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoadPostsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}