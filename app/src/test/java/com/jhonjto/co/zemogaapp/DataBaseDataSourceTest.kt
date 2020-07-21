package com.jhonjto.co.zemogaapp

import com.jhonjto.co.data.common.Resource
import com.jhonjto.co.data.repository.DbPostsRepositoryImpl
import com.jhonjto.co.data.source.DataBaseDataSource
import com.jhonjto.co.data.source.RemoteDataSource
import com.jhonjto.co.domain.DomainPostsItem
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by jhon on 20/07/2020
 */
@RunWith(MockitoJUnitRunner::class)
class DataBaseDataSourceTest {

    @Mock
    lateinit var databaseDataSource: DataBaseDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var dbPostsRepositoryImpl: DbPostsRepositoryImpl

    @Before
    fun setup() {
        dbPostsRepositoryImpl =
            DbPostsRepositoryImpl(
                databaseDataSource,
                remoteDataSource
            )
    }

    @Test
    fun `getDataBase invokes calls to local test`() {
        runBlocking {
            val posts: List<DomainPostsItem> = listOf(mockedPosts)

            whenever(databaseDataSource.getAllPosts()).thenReturn(posts)

            val result = databaseDataSource.getAllPosts()

            assertEquals(posts, result)
        }
    }

    @Test
    fun `getDataRemote invokes calls to remote test`() {
        runBlocking {
            val posts: Resource<List<DomainPostsItem>> = Resource.success(listOf(mockedPosts))

            whenever(remoteDataSource.getAllPosts()).thenReturn(posts)

            val result = remoteDataSource.getAllPosts()

            assertEquals(posts, result)
        }
    }
}