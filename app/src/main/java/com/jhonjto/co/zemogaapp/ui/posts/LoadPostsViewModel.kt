package com.jhonjto.co.zemogaapp.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhonjto.co.data.common.Resource
import com.jhonjto.co.data.common.Status.*
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.usecases.GetAllPostsFromDb
import com.jhonjto.co.usecases.GetPostIsReadedFromDb
import com.jhonjto.co.usecases.UpDatePostFromDb
import com.jhonjto.co.zemogaapp.common.ScopeViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by jhon on 18/07/2020
 */
class LoadPostsViewModel(
    private val getAllPostsFromDb: GetAllPostsFromDb,
    private val updatePostReaded: UpDatePostFromDb,
    private val getPostIsReadedFromDb: GetPostIsReadedFromDb
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

    private val _readed = MutableLiveData<UiModel>()
    val readed: LiveData<UiModel> = _readed

    sealed class UiModel {
        object Loading : UiModel()
        class Message(val message: String) : UiModel()
        class Content(val posts: List<DomainPostsItem>) : UiModel()
        class Navigation(val posts: DomainPostsItem) : UiModel()
        class UpdatePost(val id: Int) : UiModel()
    }

    private fun loadData() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(getAllPostsFromDb.invoke())
        }
    }

    fun updatePostReaded(id: Int, isReaded: Boolean) {
        launch {
            _readed.value = UiModel.UpdatePost(updatePostReaded.invoke(id, isReaded))
            _model.value = UiModel.Content(getAllPostsFromDb.invoke())
        }
    }

    private fun showPostDetail(domainPostsItem: Resource<DomainPostsItem>) {
        when (domainPostsItem.status) {
            SUCCESS -> {
                domainPostsItem.data?.let {
                    //_model.value = UiModel.Content(it)
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