package com.jhonjto.co.data.repository

import com.jhonjto.co.domain.DomainPostsItem

/**
 * Created by jhon on 19/07/2020
 */
interface DbPostsRepository {
    suspend fun getAllPosts(): List<DomainPostsItem>
    suspend fun upDatePostReadedRepository(id: Int, isReaded: Boolean) : Int
    suspend fun getIsReaded(id: Int) : List<DomainPostsItem>
    suspend fun upDatePostFavoriteRepository(id: Int, isFavorite: Boolean) : Int
    suspend fun getIsFavorite(id: Int) : List<DomainPostsItem>
    suspend fun findById(id: Int) : DomainPostsItem
}