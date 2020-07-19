package com.jhonjto.co.zemogaapp.data.source

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

    private val postsDao = appDatabase.postsDao()

    override suspend fun getAllPosts(): List<DomainPostsItem> = withContext(Dispatchers.IO) {
        postsDao.getAll().map {
            it.toDomainPosts()
        }
    }

    override suspend fun savePosts(domainPostsItem: List<DomainPostsItem>) = withContext(Dispatchers.IO) {
        val postsList = domainPostsItem.map {
            it.toDataBaseMovie()
        }
        postsDao.insertPosts(postsList)
    }

    override suspend fun updatePosts(domainPostsItem: List<DomainPostsItem>) = withContext(Dispatchers.IO) {
        val postsLIst = domainPostsItem.map {
            it.toDataBaseMovie()
        }
        postsDao.updatePosts(postsLIst)
    }

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) {
        (postsDao.postsCount()) <= 0
    }
}