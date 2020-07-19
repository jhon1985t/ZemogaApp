package com.jhonjto.co.zemogaapp.data.source

import com.jhonjto.co.data.common.Resource
import com.jhonjto.co.data.source.DataBaseDataSource
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.zemogaapp.data.database.AppDatabase
import com.jhonjto.co.zemogaapp.data.toDataBaseMovie
import com.jhonjto.co.zemogaapp.data.toDomainPosts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by jhon on 18/07/2020
 */
class RoomDataSource(private val appDatabase: AppDatabase) : DataBaseDataSource {

    override suspend fun getAllPosts(): List<DomainPostsItem> = withContext(Dispatchers.IO) {
        appDatabase.postsDao().getAll().map {
            it.toDomainPosts()
        }
    }

    override suspend fun savePosts(domainPostsItem: List<DomainPostsItem>) {
        val postsList = domainPostsItem.map {
            it.toDataBaseMovie()
        }

        appDatabase.postsDao().insertPosts(postsList)
    }

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) {
        (appDatabase.postsDao().postsCount()) <= 0
    }
}