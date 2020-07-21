package com.jhonjto.co.zemogaapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jhonjto.co.usecases.DeleteAllPostsFromDb
import com.jhonjto.co.usecases.GetAllPostsFromDb
import com.jhonjto.co.usecases.UpDatePostFromDb
import com.jhonjto.co.zemogaapp.ui.posts.LoadPostsViewModel
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by jhon on 20/07/2020
 */
@RunWith(MockitoJUnitRunner::class)
class PostsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getAllPostsFromDb: GetAllPostsFromDb

    @Mock
    lateinit var upDatePostFromDb: UpDatePostFromDb

    @Mock
    lateinit var deleteAllPostsFromDb: DeleteAllPostsFromDb

    @Mock
    lateinit var observer: Observer<LoadPostsViewModel.UiModel>

    private lateinit var loadPostsViewModel: LoadPostsViewModel

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        loadPostsViewModel =
            LoadPostsViewModel(
                Dispatchers.Unconfined,
                getAllPostsFromDb,
                upDatePostFromDb,
                deleteAllPostsFromDb
            )
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()

        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `load data`() = runBlockingTest {
        val posts = listOf(mockedPosts)
        val postId = 1

        whenever(getAllPostsFromDb.invoke()).thenReturn(posts)
        whenever(upDatePostFromDb.invoke(postId, true)).thenReturn(1)
        whenever(deleteAllPostsFromDb.invoke()).thenReturn(Unit)

        loadPostsViewModel.model.observeForever(observer)

        verify(observer).onChanged(LoadPostsViewModel.UiModel.Content(posts))
        verify(observer).onChanged(LoadPostsViewModel.UiModel.UpdatePost(1))
    }
}