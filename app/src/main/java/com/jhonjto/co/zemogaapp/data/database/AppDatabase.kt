package com.jhonjto.co.zemogaapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jhonjto.co.zemogaapp.data.database.dao.PostsDao
import com.jhonjto.co.zemogaapp.data.database.dao.UserDao
import com.jhonjto.co.zemogaapp.data.database.entity.DataBasePostsItem
import com.jhonjto.co.zemogaapp.data.database.entity.DataBaseUserDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by jhon on 18/07/2020
 */
private const val DATA_BASE = "appDatabase.db"

@Database(entities = [DataBasePostsItem::class, DataBaseUserDetails::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            return INSTANCE ?: synchronized(AppDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,

                    DATA_BASE
                ).fallbackToDestructiveMigration().addCallback(AppDatabaseCallback()).build()
                INSTANCE = instance
                instance
            }
        }

        private class AppDatabaseCallback : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                CoroutineScope(Dispatchers.IO).launch {
                    INSTANCE?.postsDao()?.deleteAll()
                }
            }
        }
    }
}