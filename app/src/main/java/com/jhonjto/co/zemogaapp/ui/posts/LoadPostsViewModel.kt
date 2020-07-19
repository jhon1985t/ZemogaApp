package com.jhonjto.co.zemogaapp.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhonjto.co.data.common.Resource
import com.jhonjto.co.data.common.Status.*
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.usecases.GetAllPostsFromDb
import com.jhonjto.co.zemogaapp.common.ScopeViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by jhon on 18/07/2020
 */
class LoadPostsViewModel(
    private val getAllPostsFromDb: GetAllPostsFromDb
) : ScopeViewModel() {

    init {
        initScope()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) {
                loadData()
            }
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        class Posts(val posts: DomainPostsItem) : UiModel()
        class Message(val message: String) : UiModel()
        class Content(val posts: List<DomainPostsItem>) : UiModel()
        class Navigation(val posts: DomainPostsItem) : UiModel()
        class RefreshPosts(val posts: List<DomainPostsItem>) : UiModel()
    }

    private fun loadData() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(getAllPostsFromDb.invoke())
        }
    }

    private fun showPostDetail(domainPostsItem: Resource<DomainPostsItem>) {
        when (domainPostsItem.status) {
            SUCCESS -> {
                domainPostsItem.data?.let {
                    _model.value = UiModel.Posts(it)
                } ?: kotlin.run {
                    _model.value = UiModel.Message("Ocurrio un error al cargar la información")
                }
            }
            ERROR -> {
                _model.value = UiModel.Message("Ocurrio un error al cargar la información")
                Timber.e(domainPostsItem.message)
            }
            LOADING -> {
                _model.value = UiModel.Loading
            }
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}