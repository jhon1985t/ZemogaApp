package com.jhonjto.co.usecases

import com.jhonjto.co.data.repository.DbPostsRepository

/**
 * Created by jhon on 19/07/2020
 */
class UpDatePostFromDb(private val dbPostsRepository: DbPostsRepository) {
    suspend fun invoke(id: Int, isReaded: Boolean) : Int = dbPostsRepository.upDatePostReadedRepository(id, isReaded)
}