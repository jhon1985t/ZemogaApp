package com.jhonjto.co.zemogaapp.data.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by jhon on 18/07/2020
 */
@Entity
@Parcelize
data class DataBasePostsItem (
    @ColumnInfo val body: String,
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val userId: Int
) : Parcelable