package com.jhonjto.co.data.source

import com.jhonjto.co.domain.DomainPostsItem

/**
 * Created by jhon on 18/07/2020
 */
interface DataBaseDataSource {

    suspend fun getAllPosts() : List<DomainPostsItem>
    suspend fun savePosts(domainPostsItem: List<DomainPostsItem>)
    suspend fun isEmpty(): Boolean
}