package com.jhonjto.co.zemogaapp.data.source

import com.jhonjto.co.data.common.Resource
import com.jhonjto.co.data.source.RemoteDataSource
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.zemogaapp.data.ResponseHandler
import com.jhonjto.co.zemogaapp.data.server.JsonDbServices
import com.jhonjto.co.zemogaapp.data.toDomainPosts

/**
 * Created by jhon on 18/07/2020
 */
class RetrofitDataSource(
    private val postsDbServices: JsonDbServices
) : RemoteDataSource {

    override suspend fun getAllPosts(): Resource<List<DomainPostsItem>> {
        return try {
            val posts = postsDbServices.getPosts().run {
                map {
                    it.toDomainPosts()
                }
            }
            ResponseHandler().handleSuccess(posts)
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }
}