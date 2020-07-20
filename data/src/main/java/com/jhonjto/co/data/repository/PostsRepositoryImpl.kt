package com.jhonjto.co.data.repository

import com.jhonjto.co.data.common.Resource
import com.jhonjto.co.data.source.DataBaseDataSource
import com.jhonjto.co.data.source.RemoteDataSource
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.domain.DomainUserDetails

/**
 * Created by jhon on 18/07/2020
 */
class PostsRepositoryImpl(
    private val dataBaseDataSource: DataBaseDataSource,
    private val remoteDataSource: RemoteDataSource
) : PostsRepository {

    override suspend fun getAllPosts(): List<DomainPostsItem> {
        if (dataBaseDataSource.isEmpty()) {
            val posts = remoteDataSource.getAllPosts()
            posts.data?.let {
                dataBaseDataSource.savePosts(it)
            }
        }
        return dataBaseDataSource.getAllPosts()
    }

    override suspend fun getUserDetails(id: Int): Resource<DomainUserDetails> {
        /*if (dataBaseDataSource.isEmpty()) {
            val user = remoteDataSource.getUserDetails(id)
            user.data?.let {
                dataBaseDataSource.saveUserDetails(it)
            }
        }
        return dataBaseDataSource.getUserDetails()*/
        return remoteDataSource.getUserDetails(id)
    }
}