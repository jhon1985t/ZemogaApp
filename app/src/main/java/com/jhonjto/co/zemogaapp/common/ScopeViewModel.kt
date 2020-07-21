package com.jhonjto.co.zemogaapp.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by jhon on 18/07/2020
 */
abstract class ScopeViewModel(
    uiDispatcher: CoroutineDispatcher
) : ViewModel(), Scope by Scope.Impl() {
    init {
        initScope()
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}