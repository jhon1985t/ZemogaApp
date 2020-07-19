package com.jhonjto.co.zemogaapp.common

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by jhon on 18/07/2020
 */
interface Scope : CoroutineScope {

    class Impl : Scope {
        override lateinit var job: Job
    }

    var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun initScope() {
        job = SupervisorJob()
    }

    fun destroyScope() {
        job.cancel()
    }
}