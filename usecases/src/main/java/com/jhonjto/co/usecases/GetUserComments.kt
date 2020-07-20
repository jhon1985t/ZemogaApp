package com.jhonjto.co.usecases

import com.jhonjto.co.data.common.Resource
import com.jhonjto.co.data.repository.PostsRepository
import com.jhonjto.co.domain.DomainUserComments

/**
 * Created by jhon on 20/07/2020
 */
class GetUserComments(private val postsRepository: PostsRepository) {
    suspend fun invoke(id: Int) : Resource<DomainUserComments> = postsRepository.getUserComments(id)
}