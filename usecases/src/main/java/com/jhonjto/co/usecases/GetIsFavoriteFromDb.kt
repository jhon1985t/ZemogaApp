package com.jhonjto.co.usecases

import com.jhonjto.co.data.repository.DbPostsRepository
import com.jhonjto.co.domain.DomainPostsItem

/**
 * Created by jhon on 20/07/2020
 */
class GetIsFavoriteFromDb(private val dbPostsRepository: DbPostsRepository) {
    suspend fun invoke(isFavorite: Boolean) : List<DomainPostsItem> = dbPostsRepository.getFavorite(isFavorite)
}