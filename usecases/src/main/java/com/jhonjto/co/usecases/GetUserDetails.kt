package com.jhonjto.co.usecases

import com.jhonjto.co.data.common.Resource
import com.jhonjto.co.data.repository.PostsRepository
import com.jhonjto.co.domain.DomainUserDetails

/**
 * Created by jhon on 20/07/2020
 */
class GetUserDetails(private val postsRepository: PostsRepository) {
    suspend fun invoke(id: Int) : Resource<DomainUserDetails> = postsRepository.getUserDetails(id)
}