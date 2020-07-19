package com.jhonjto.co.zemogaapp.data.database.dao

import androidx.room.*
import com.jhonjto.co.zemogaapp.data.database.entity.DataBasePostsItem

/**
 * Created by jhon on 18/07/2020
 */
@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPosts(posts: List<DataBasePostsItem>)

    @Update
    suspend fun updatePosts(dataBasePostsItem: List<DataBasePostsItem>)

    @Query("SELECT * FROM DataBasePostsItem")
    suspend fun getAll(): List<DataBasePostsItem>

    @Query("DELETE FROM DataBasePostsItem")
    suspend fun deleteAll()

    @Query("SELECT * FROM DataBasePostsItem WHERE id = :id")
    suspend fun findById(id: Int): DataBasePostsItem

    @Query("SELECT COUNT(id) FROM DataBasePostsItem")
    suspend fun postsCount(): Int
}