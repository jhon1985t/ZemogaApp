package com.jhonjto.co.zemogaapp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jhonjto.co.zemogaapp.R
import com.jhonjto.co.zemogaapp.ui.detail.DetailViewModel.UiModel
import com.jhonjto.co.zemogaapp.ui.detail.DetailViewModel.UiModel.Content
import com.jhonjto.co.zemogaapp.ui.detail.DetailViewModel.UiModel.User
import kotlinx.android.synthetic.main.content_detail.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : AppCompatActivity() {

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

        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: UiModel) {
        when (model) {
            is Content -> with(model.post) {
                tvDescriptionContext.text = body
                viewModel.loadUserDetails(model.post.id)
            }
            is User -> with(model.userDetails) {
                this.data.let {
                    tvUserNameFill.text = it?.name
                    tvUserEmailFill.text = it?.email
                    tvUserPhoneFill.text = it?.phone
                    tvUserWebsiteFill.text = it?.website
                }
            }
        }
    }
}