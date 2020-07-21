package com.jhonjto.co.data.repository

import com.jhonjto.co.data.source.DataBaseDataSource
import com.jhonjto.co.data.source.RemoteDataSource
import com.jhonjto.co.domain.DomainPostsItem

/**
 * Created by jhon on 19/07/2020
 */
class DbPostsRepositoryImpl(
    private val dataBaseDataSource: DataBaseDataSource,
    private val remoteDataSource: RemoteDataSource
) : DbPostsRepository {

    override suspend fun getAllPosts(): List<DomainPostsItem> {
        if (dataBaseDataSource.isEmpty()) {
            val posts = remoteDataSource.getAllPosts()
            posts.data?.let {
                dataBaseDataSource.savePosts(it)
            }
        }
        return dataBaseDataSource.getAllPosts()
    }

    override suspend fun deleteAllPosts() {
        return dataBaseDataSource.deleteAllPosts()
    }

    override suspend fun upDatePostReadedRepository(id: Int, isReaded: Boolean) : Int {
        return dataBaseDataSource.updatePostReaded(id, isReaded)
    }

    override suspend fun getIsReaded(id: Int): List<DomainPostsItem> {
        return dataBaseDataSource.getIsReaded(id)
    }

    override suspend fun upDatePostFavoriteRepository(id: Int, isFavorite: Boolean): Int {
        return dataBaseDataSource.updatePostFavorite(id, isFavorite)
    }

    override suspend fun getIsFavorite(id: Int): List<DomainPostsItem> {
        return dataBaseDataSource.getPostFavorite(id)
    }

    override suspend fun getFavorite(isFavorite: Boolean): List<DomainPostsItem> {
        return dataBaseDataSource.getIsFavorite(isFavorite)
    }

    override suspend fun findById(id: Int): DomainPostsItem {
        return dataBaseDataSource.findById(id)
    }
}