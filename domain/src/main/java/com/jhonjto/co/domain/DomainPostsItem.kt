package com.jhonjto.co.domain

/**
 * Created by jhon on 18/07/2020
 */
data class DomainPostsItem (
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    val isReaded: Boolean = false,
    val isFavorite: Boolean = false
)