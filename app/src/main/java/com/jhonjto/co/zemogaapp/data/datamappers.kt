package com.jhonjto.co.zemogaapp.data

import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.zemogaapp.data.database.entity.DataBasePostsItem
import com.jhonjto.co.zemogaapp.data.server.ServerPostsItem

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

fun DataBasePostsItem.toDomainPosts(): DomainPostsItem {
    return DomainPostsItem(
        body,
        id,
        title,
        userId
    )
}

fun DomainPostsItem.toDataBaseMovie(): DataBasePostsItem {
    return DataBasePostsItem(
        body,
        id,
        title,
        userId
    )
}