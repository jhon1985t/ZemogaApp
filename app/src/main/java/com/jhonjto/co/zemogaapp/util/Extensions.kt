package com.jhonjto.co.zemogaapp.util

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * Created by jhon on 20/07/2020
 */
inline fun <reified T : Activity> Context.intentFor(body: Intent.() -> Unit): Intent =
    Intent(this, T::class.java).apply(body)

inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit) {
    startActivity(intentFor<T>(body))
}