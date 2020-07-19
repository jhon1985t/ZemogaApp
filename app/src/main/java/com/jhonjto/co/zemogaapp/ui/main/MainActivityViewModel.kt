package com.jhonjto.co.zemogaapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.usecases.GetAllPosts
import com.jhonjto.co.zemogaapp.common.ScopeViewModel
import kotlinx.coroutines.launch

/**
 * Created by jhon on 18/07/2020
 */
class MainActivityViewModel(
    private val getAllPosts: GetAllPosts
) : ScopeViewModel() {

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
        data class Content(val posts: List<DomainPostsItem>) : UiModel()
        data class Navigation(val posts: DomainPostsItem) : UiModel()
        data class RefreshPosts(val posts: List<DomainPostsItem>) : UiModel()
    }

    init {
        initScope()
    }

    private fun refresh() {
        launch {
            _model.value = UiModel.RefreshPosts(getAllPosts.invoke())
        }
    }

    private fun loadData() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(getAllPosts.invoke())
        }
    }

    fun onPostsClicked(postsItem: DomainPostsItem) {
        _model.value = UiModel.Navigation(postsItem)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}