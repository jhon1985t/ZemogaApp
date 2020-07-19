package com.jhonjto.co.zemogaapp.data.server

import retrofit2.http.GET

/**
 * Created by jhon on 18/07/2020
 */
interface JsonDbServices {

    @GET("/posts")
    suspend fun getPosts(): List<ServerPostsItem>
}