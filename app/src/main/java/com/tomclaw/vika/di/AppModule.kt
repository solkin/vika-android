package com.tomclaw.vika.di

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.tomclaw.cache.DiskLruCache
import com.tomclaw.vika.core.CoreDbHelper
import com.tomclaw.vika.core.Chats
import com.tomclaw.vika.core.ChatsImpl
import com.tomclaw.vika.util.Logger
import com.tomclaw.vika.util.LoggerImpl
import com.tomclaw.vika.util.SchedulersFactory
import com.tomclaw.vika.util.SchedulersFactoryImpl
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    internal fun provideContext(): Context = app

    @Provides
    @Singleton
    internal fun provideSchedulersFactory(): SchedulersFactory = SchedulersFactoryImpl()

    @Provides
    @Singleton
    fun provideChats(db: SQLiteDatabase): Chats = ChatsImpl(db)

    @Provides
    @Singleton
    fun provideSqLiteDatabase(dbHelper: CoreDbHelper): SQLiteDatabase = dbHelper.writableDatabase

    @Provides
    @Singleton
    fun provideCoreDbHelper(): CoreDbHelper = CoreDbHelper(app)

    @Provides
    @Singleton
    fun provideFilesDir(): File = app.filesDir

    @Provides
    @Singleton
    internal fun provideLogger(): Logger = LoggerImpl()

    @Provides
    @Singleton
    fun provideLruCache(): DiskLruCache {
        val cacheDir = File(app.cacheDir, "share")
        return DiskLruCache.create(cacheDir, LRU_CACHE_SIZE)
    }

}

const val LRU_CACHE_SIZE = 25L * 1024 * 1024
