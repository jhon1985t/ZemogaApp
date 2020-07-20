package com.jhonjto.co.data.source

import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.domain.DomainUserDetails

/**
 * Created by jhon on 18/07/2020
 */
interface DataBaseDataSource {

    suspend fun getAllPosts() : List<DomainPostsItem>
    suspend fun savePosts(domainPostsItem: List<DomainPostsItem>)
    suspend fun updatePosts(domainPostsItem: List<DomainPostsItem>)
    suspend fun updatePostReaded(id: Int, isReaded: Boolean) : Int
    suspend fun getIsReaded(id: Int) : List<DomainPostsItem>
    suspend fun findById(id: Int) : DomainPostsItem
    suspend fun updatePostFavorite(id: Int, isFavorite: Boolean) : Int
    suspend fun getPostFavorite(id: Int) : List<DomainPostsItem>
    suspend fun isEmpty(): Boolean

    /*suspend fun getUserDetails() : List<DomainUserDetails>
    suspend fun saveUserDetails(domainUserDetails: List<DomainUserDetails>)*/
}