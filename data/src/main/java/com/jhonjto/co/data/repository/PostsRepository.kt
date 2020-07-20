package com.jhonjto.co.data.repository

import com.jhonjto.co.domain.DomainPostsItem

/**
 * Created by jhon on 18/07/2020
 */
interface PostsRepository {
    suspend fun getAllPosts(): List<DomainPostsItem>
}