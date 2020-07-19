package com.jhonjto.co.zemogaapp.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.jhonjto.co.zemogaapp.data.server.ServerPostsItem
import timber.log.Timber

/**
 * Created by jhon on 18/07/2020
 */
class Converters {

    @TypeConverter
    fun toListPosts(json: String?) : List<ServerPostsItem>? {
        return try {
            return json?.let {
                Gson().fromJson(it, Array<ServerPostsItem>::class.java).toList()
            }
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }

    @TypeConverter
    fun toPostsData(posts: List<ServerPostsItem>) : String? {
        return try {
            Gson().toJson(posts).toString()
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }
}