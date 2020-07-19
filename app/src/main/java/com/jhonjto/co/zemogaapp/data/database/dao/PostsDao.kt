package com.jhonjto.co.zemogaapp.data.database.dao

import androidx.room.*
import com.jhonjto.co.zemogaapp.data.database.entity.DataBasePostsItem

/**
 * Created by jhon on 18/07/2020
 */
@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPosts(posts: List<DataBasePostsItem>)

    @Update
    fun updatePosts(dataBasePostsItem: DataBasePostsItem)

    @Query("SELECT * FROM DataBasePostsItem")
    fun getAll(): List<DataBasePostsItem>

    @Query("DELETE FROM DataBasePostsItem")
    fun deleteAll()

    @Query("SELECT * FROM DataBasePostsItem WHERE id = :id")
    fun findById(id: Int): DataBasePostsItem

    @Query("SELECT COUNT(id) FROM DataBasePostsItem")
    fun postsCount(): Int
}