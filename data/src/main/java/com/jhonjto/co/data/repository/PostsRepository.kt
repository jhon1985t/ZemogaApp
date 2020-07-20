package com.jhonjto.co.data.repository

import com.jhonjto.co.data.common.Resource
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.domain.DomainUserComments
import com.jhonjto.co.domain.DomainUserDetails

/**
 * Created by jhon on 18/07/2020
 */
interface PostsRepository {
    suspend fun getAllPosts() : List<DomainPostsItem>
    suspend fun getUserDetails(id: Int) : Resource<DomainUserDetails>
    suspend fun getUserComments(id: Int) : Resource<DomainUserComments>
}