package com.jhonjto.co.zemogaapp

import android.app.Application
import com.jhonjto.co.data.repository.DbPostsRepository
import com.jhonjto.co.data.repository.DbPostsRepositoryImpl
import com.jhonjto.co.data.repository.PostsRepository
import com.jhonjto.co.data.repository.PostsRepositoryImpl
import com.jhonjto.co.data.source.DataBaseDataSource
import com.jhonjto.co.data.source.RemoteDataSource
import com.jhonjto.co.usecases.*
import com.jhonjto.co.zemogaapp.data.database.AppDatabase
import com.jhonjto.co.zemogaapp.data.server.TheJsonDb
import com.jhonjto.co.zemogaapp.data.source.RetrofitDataSource
import com.jhonjto.co.zemogaapp.data.source.RoomDataSource
import com.jhonjto.co.zemogaapp.ui.comments.ShowFavorites
import com.jhonjto.co.zemogaapp.ui.comments.ShowFavoritesViewModel
import com.jhonjto.co.zemogaapp.ui.detail.DetailActivity
import com.jhonjto.co.zemogaapp.ui.detail.DetailViewModel
import com.jhonjto.co.zemogaapp.ui.main.MainActivity
import com.jhonjto.co.zemogaapp.ui.main.MainViewModel
import com.jhonjto.co.zemogaapp.ui.posts.LoadPosts
import com.jhonjto.co.zemogaapp.ui.posts.LoadPostsViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by jhon on 18/07/2020
 */
fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single { TheJsonDb.jsonDbServices }
    single { AppDatabase.getInstance(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    factory<DataBaseDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { RetrofitDataSource(get()) }
}

val dataModule = module {
    factory<PostsRepository> { PostsRepositoryImpl(get(), get()) }
    factory<DbPostsRepository> { DbPostsRepositoryImpl(get(), get()) }
}

private val scopesModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get()) }
        scoped { GetAllPosts(get()) }
    }

    scope(named<DetailActivity>()) {
        viewModel { (postId: Int) ->
            DetailViewModel(
                postId = postId,
                updatePostFavoriteFromDb = get(),
                getPostFindByIdFromDb = get(),
                getUserDetails = get()
            )
        }
        scoped { UpdatePostFavoriteFromDb(get()) }
        scoped { GetPostFindByIdFromDb(get()) }
        scoped { GetUserDetails(get()) }
    }

    scope(named<LoadPosts>()) {
        viewModel { LoadPostsViewModel(get(), get()) }
        scoped { GetAllPostsFromDb(get()) }
        scoped { UpDatePostFromDb(get()) }
        scoped { GetPostIsReadedFromDb(get()) }
    }

    scope(named<ShowFavorites>()) {
        viewModel { ShowFavoritesViewModel() }
    }
}