package com.jhonjto.co.zemogaapp.common

import androidx.lifecycle.ViewModel

/**
 * Created by jhon on 18/07/2020
 */
abstract class ScopeViewModel : ViewModel(), Scope by Scope.Impl() {
    init {
        initScope()
    }

    override fun onCleared() {
        super.onCleared()
    }
}