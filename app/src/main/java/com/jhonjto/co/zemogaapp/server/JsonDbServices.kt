package com.jhonjto.co.zemogaapp.server

import com.jhonjto.co.zemogaapp.data.database.Posts
import retrofit2.http.GET

/**
 * Created by jhon on 18/07/2020
 */
interface JsonDbServices {

    @GET("/posts")
    suspend fun getPosts(): Posts
}