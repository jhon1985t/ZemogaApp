package com.jhonjto.co.usecases

import com.jhonjto.co.data.repository.DbPostsRepository
import com.jhonjto.co.domain.DomainPostsItem

/**
 * Created by jhon on 19/07/2020
 */
class GetPostIsReadedFromDb(private val dbPostsRepository: DbPostsRepository) {
    suspend fun invoke(id: Int) : List<DomainPostsItem> = dbPostsRepository.getIsReaded(id)
}