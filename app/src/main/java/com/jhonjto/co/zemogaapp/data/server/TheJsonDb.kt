package com.jhonjto.co.zemogaapp.data.server

import com.jhonjto.co.zemogaapp.BuildConfig
import com.jhonjto.co.zemogaapp.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by jhon on 18/07/2020
 */
object TheJsonDb {

    private fun addInterceptor() : HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun retrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder().addInterceptor(addInterceptor())
            .connectTimeout(Constants.connectTimeout, TimeUnit.MINUTES)
            .readTimeout(Constants.readTimeOut, TimeUnit.SECONDS)
            .writeTimeout(Constants.writeTimeout, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val jsonDbServices: JsonDbServices by lazy { retrofit().create(JsonDbServices::class.java) }
}