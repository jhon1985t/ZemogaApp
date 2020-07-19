package com.jhonjto.co.data.repository

import com.jhonjto.co.data.source.DataBaseDataSource
import com.jhonjto.co.domain.DomainPostsItem

/**
 * Created by jhon on 19/07/2020
 */
class DbPostsRepositoryImpl(
    private val dataBaseDataSource: DataBaseDataSource
) : DbPostsRepository {

    override suspend fun getAllPosts(): List<DomainPostsItem> {
        return dataBaseDataSource.getAllPosts()
    }
}