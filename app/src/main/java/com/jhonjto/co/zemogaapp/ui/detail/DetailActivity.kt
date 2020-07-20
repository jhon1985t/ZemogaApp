package com.jhonjto.co.zemogaapp.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.zemogaapp.R
import com.jhonjto.co.zemogaapp.adapters.CommentsAdapter
import com.jhonjto.co.zemogaapp.ui.detail.DetailViewModel.UiModel
import com.jhonjto.co.zemogaapp.ui.detail.DetailViewModel.UiModel.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.content_detail.view.*
import kotlinx.coroutines.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.coroutines.CoroutineContext

class DetailActivity : AppCompatActivity() {

    private lateinit var adapter: CommentsAdapter

    companion object {
        const val POST = "DetailActivity:post"
    }

    private val viewModel: DetailViewModel by lifecycleScope.viewModel(this) {
        parametersOf(intent.getIntExtra(POST, -1))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = CommentsAdapter()
        rvComments.layoutManager = LinearLayoutManager(this)
        rvComments.adapter = adapter
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: UiModel) {
        when (model) {
            is Content ->
                with(model.post) {
                    tvDescriptionContext.text = body
                    viewModel.loadUserDetails(model.post.id)
                    viewModel.loadUserComments(model.post.id)
                }

            is User -> with(model.userDetails) {
                this.data.let {
                    tvUserNameFill.text = it?.name
                    tvUserEmailFill.text = it?.email
                    tvUserPhoneFill.text = it?.phone
                    tvUserWebsiteFill.text = it?.website
                }
            }
            is Comments -> adapter.comments = listOf(model.userComments.data!!)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorites_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> addToFavorites()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addToFavorites() {
        viewModel.onFavoriteClicked(true)
    }
}