package com.jhonjto.co.zemogaapp.data.database.dao

import androidx.room.*
import com.jhonjto.co.zemogaapp.data.database.entity.DataBaseUserDetails

/**
 * Created by jhon on 20/07/2020
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<DataBaseUserDetails>)

    @Query("SELECT * FROM DataBaseUserDetails")
    fun getAll() : List<DataBaseUserDetails>

    @Query("DELETE FROM DataBaseUserDetails")
    suspend fun deleteAll()

    @Query("SELECT COUNT(id) FROM DataBaseUserDetails")
    suspend fun postsCount() : Int
}