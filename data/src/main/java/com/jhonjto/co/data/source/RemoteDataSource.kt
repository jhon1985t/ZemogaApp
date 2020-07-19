package com.jhonjto.co.data.source

import com.jhonjto.co.data.common.Resource
import com.jhonjto.co.domain.DomainPostsItem

/**
 * Created by jhon on 18/07/2020
 */
interface RemoteDataSource {
    suspend fun getAllPosts(): Resource<List<DomainPostsItem>>
}