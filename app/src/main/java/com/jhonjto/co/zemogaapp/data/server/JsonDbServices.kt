package com.jhonjto.co.zemogaapp.data.server

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by jhon on 18/07/2020
 */
interface JsonDbServices {

    @GET("/posts")
    suspend fun getPosts(): List<ServerPostsItem>

    @GET("/users/{id}")
    suspend fun getUserDetail(
        @Path("id") id: Int
    ) : ServerUserDetail

    @GET("/comments/{id}")
    suspend fun getUserComments(
        @Path("id") id: Int
    ) : ServerUserComments
}