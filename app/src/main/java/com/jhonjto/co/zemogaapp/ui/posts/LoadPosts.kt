package com.jhonjto.co.zemogaapp.ui.posts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jhonjto.co.zemogaapp.adapters.PostsAdapter
import com.jhonjto.co.zemogaapp.databinding.LoadPostsFragmentBinding
import com.jhonjto.co.zemogaapp.ui.posts.LoadPostsViewModel.UiModel
import com.jhonjto.co.zemogaapp.ui.posts.LoadPostsViewModel.UiModel.*
import com.jhonjto.co.zemogaapp.util.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

/**
 * Created by jhon on 18/07/2020
 */
class LoadPosts : Fragment() {

    private lateinit var binding: LoadPostsFragmentBinding
    private lateinit var adapter: PostsAdapter
    private val viewModel: LoadPostsViewModel by lifecycleScope.viewModel(this)

    private var listener: Listener? = null

    interface Listener {
        fun navigateTo(id: Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoadPostsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = PostsAdapter(viewModel::updatePostReaded)
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.postsRecyclerView.adapter = adapter

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.postsRecyclerView.adapter as PostsAdapter
                adapter.remoteAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.postsRecyclerView)

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { post ->
                listener?.navigateTo(post.id)
            }
        })
    }

    private fun updateUi(model: UiModel) {
        progress.visibility = if (model == Loading) View.VISIBLE else View.GONE

        when (model) {
            is Content -> adapter.posts = model.posts
            is UpdatePost -> viewModel.updatePostReaded(model.id, true)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        }
    }
}