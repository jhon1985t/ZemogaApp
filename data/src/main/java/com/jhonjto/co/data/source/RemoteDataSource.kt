package com.jhonjto.co.data.source

import com.jhonjto.co.data.common.Resource
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.domain.DomainUserComments
import com.jhonjto.co.domain.DomainUserDetails

/**
 * Created by jhon on 18/07/2020
 */
interface RemoteDataSource {

    suspend fun getAllPosts() : Resource<List<DomainPostsItem>>
    suspend fun getUserDetails(id: Int) : Resource<DomainUserDetails>
    suspend fun getUserComments(id: Int) : Resource<DomainUserComments>
}