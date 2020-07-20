package com.jhonjto.co.zemogaapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by jhon on 18/07/2020
 */
@Entity
data class DataBaseUserDetails(
    @ColumnInfo val email: String,
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val phone: String,
    @ColumnInfo val username: String,
    @ColumnInfo val website: String
)