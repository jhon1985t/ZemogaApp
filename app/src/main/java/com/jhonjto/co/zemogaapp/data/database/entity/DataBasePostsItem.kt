package com.jhonjto.co.zemogaapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by jhon on 18/07/2020
 */
@Entity
data class DataBasePostsItem (
    @ColumnInfo val body: String,
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val userId: Int,
    @ColumnInfo val isReaded: Boolean = false,
    @ColumnInfo val isFavorite: Boolean = false
)