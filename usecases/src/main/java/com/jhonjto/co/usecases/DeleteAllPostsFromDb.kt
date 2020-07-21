package com.jhonjto.co.usecases

import com.jhonjto.co.data.repository.DbPostsRepository

/**
 * Created by jhon on 20/07/2020
 */
class DeleteAllPostsFromDb(private val dbPostsRepository: DbPostsRepository) {
    suspend fun invoke() = dbPostsRepository.deleteAllPosts()
}