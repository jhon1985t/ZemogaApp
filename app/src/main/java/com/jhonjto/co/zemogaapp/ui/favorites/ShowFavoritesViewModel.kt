package com.jhonjto.co.zemogaapp.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.usecases.GetIsFavoriteFromDb
import com.jhonjto.co.zemogaapp.common.ScopeViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class ShowFavoritesViewModel(
    uiDispatcher: CoroutineDispatcher,
    private val getIsFavoriteFromDb: GetIsFavoriteFromDb
) : ScopeViewModel(uiDispatcher) {
    // TODO: Implement the ViewModel
    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) {
                load()
            }
            return _model
        }

    sealed class UiModel {
        object Loading : UiModel()
        data class Content(val posts: List<DomainPostsItem>) : UiModel()
    }

    private fun load() = launch {
        _model.value = UiModel.Content(getIsFavoriteFromDb.invoke(true))
    }
}