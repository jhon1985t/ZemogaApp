package com.jhonjto.co.zemogaapp.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.usecases.GetAllPostsFromDb
import com.jhonjto.co.usecases.UpDatePostFromDb
import com.jhonjto.co.zemogaapp.common.Event
import com.jhonjto.co.zemogaapp.common.ScopeViewModel
import kotlinx.coroutines.launch

/**
 * Created by jhon on 18/07/2020
 */
class LoadPostsViewModel(
    private val getAllPostsFromDb: GetAllPostsFromDb,
    private val updatePostReaded: UpDatePostFromDb
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
    val readed : LiveData<UiModel> = _readed

    private val _navigation = MutableLiveData<Event<DomainPostsItem>>()
    val navigation : LiveData<Event<DomainPostsItem>> = _navigation

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val posts: List<DomainPostsItem>) : UiModel()
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

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}