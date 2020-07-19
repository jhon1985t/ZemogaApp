package com.jhonjto.co.usecases

import com.jhonjto.co.data.common.Resource
import com.jhonjto.co.data.repository.PostsRepository
import com.jhonjto.co.domain.DomainPostsItem

/**
 * Created by jhon on 18/07/2020
 */
class GetAllPosts(private val postsRepository: PostsRepository) {
    suspend fun invoke(): Resource<List<DomainPostsItem>> = postsRepository.getAllPosts()
}