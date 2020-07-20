package com.jhonjto.co.zemogaapp.data

import com.jhonjto.co.domain.*
import com.jhonjto.co.zemogaapp.data.database.entity.*
import com.jhonjto.co.zemogaapp.data.server.ServerPostsItem
import com.jhonjto.co.zemogaapp.data.server.ServerUserDetail

/**
 * Created by jhon on 18/07/2020
 */
fun ServerPostsItem.toDomainPosts(): DomainPostsItem {
    return DomainPostsItem(
        body,
        id,
        title,
        userId
    )
}

fun ServerUserDetail.toDomainUserDetails(): DomainUserDetails {

    return DomainUserDetails(
        email,
        id,
        name,
        phone,
        username,
        website
    )
}

fun DataBasePostsItem.toDomainPosts(): DomainPostsItem {
    return DomainPostsItem(
        body,
        id,
        title,
        userId,
        isReaded,
        isFavorite
    )
}

fun DataBaseUserDetails.toDomainUserDetails(): DomainUserDetails {
    return DomainUserDetails(
        email,
        id,
        name,
        phone,
        username,
        website
    )
}

fun DomainPostsItem.toDataBaseMovie(): DataBasePostsItem {
    return DataBasePostsItem(
        body,
        id,
        title,
        userId,
        isReaded = false,
        isFavorite = false
    )
}

fun DomainUserDetails.toDataBaseUserDetails() : DataBaseUserDetails {
    return DataBaseUserDetails(
        email,
        id,
        name,
        phone,
        username,
        website
    )
}