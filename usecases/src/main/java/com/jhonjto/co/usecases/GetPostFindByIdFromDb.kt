package com.jhonjto.co.usecases

import com.jhonjto.co.data.repository.DbPostsRepository
import com.jhonjto.co.domain.DomainPostsItem

/**
 * Created by jhon on 20/07/2020
 */
class GetPostFindByIdFromDb(private val dbPostsRepository: DbPostsRepository) {
    suspend fun invoke(id: Int) : DomainPostsItem = dbPostsRepository.findById(id)
}