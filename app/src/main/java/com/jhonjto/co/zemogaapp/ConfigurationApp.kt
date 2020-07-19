package com.jhonjto.co.zemogaapp

import android.app.Application
import com.jhonjto.co.zemogaapp.util.AndroidHelper

/**
 * Created by jhon on 18/07/2020
 */

class ConfigurationApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidHelper.init(applicationContext)
        initDI()
    }
}