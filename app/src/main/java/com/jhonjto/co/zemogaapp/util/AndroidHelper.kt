package com.jhonjto.co.zemogaapp.util

import android.content.Context

/**
 * Created by jhon on 18/07/2020
 */
object AndroidHelper {

    private var context: Context? = null

    fun init(context: Context) {
        this.context = context
    }
}