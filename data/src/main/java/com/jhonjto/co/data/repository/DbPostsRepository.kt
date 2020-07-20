package com.jhonjto.co.data.repository

import com.jhonjto.co.domain.DomainPostsItem

/**
 * Created by jhon on 19/07/2020
 */
interface DbPostsRepository {
    suspend fun getAllPosts(): List<DomainPostsItem>
    suspend fun upDatePostRepository(id: Int, isReaded: Boolean) : Int
    suspend fun getIsReaded(id: Int) : List<DomainPostsItem>
}