package com.jhonjto.co.zemogaapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhonjto.co.data.common.Resource
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.domain.DomainUserComments
import com.jhonjto.co.domain.DomainUserDetails
import com.jhonjto.co.usecases.GetPostFindByIdFromDb
import com.jhonjto.co.usecases.GetUserComments
import com.jhonjto.co.usecases.GetUserDetails
import com.jhonjto.co.usecases.UpdatePostFavoriteFromDb
import com.jhonjto.co.zemogaapp.common.ScopeViewModel
import kotlinx.coroutines.launch

/**
 * Created by jhon on 20/07/2020
 */
class DetailViewModel(
    private val postId: Int,
    private val updatePostFavoriteFromDb: UpdatePostFavoriteFromDb,
    private val getPostFindByIdFromDb: GetPostFindByIdFromDb,
    private val getUserDetails: GetUserDetails,
    private val getUserComments: GetUserComments
) : ScopeViewModel() {

    sealed class UiModel {
        object Loading : UiModel()
        data class Content(val post : DomainPostsItem) : UiModel()
        data class User(val userDetails: Resource<DomainUserDetails>) : UiModel()
        data class Comments(val userComments: Resource<DomainUserComments>) : UiModel()
        data class AddFavorite(val postId: Int) : UiModel()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findPost()
            return _model
        }

    private fun findPost() = launch {
        _model.value = UiModel.Content(getPostFindByIdFromDb.invoke(postId))
    }

    fun loadUserDetails(id: Int) = launch {
        _model.value = UiModel.User(getUserDetails.invoke(id))
    }

    fun loadUserComments(id: Int) = launch {
        _model.value = UiModel.Comments(getUserComments.invoke(id))
    }

    fun onFavoriteClicked(isFavorite: Boolean) = launch {
        _model.value = UiModel.AddFavorite(updatePostFavoriteFromDb.invoke(postId, isFavorite))
    }
}