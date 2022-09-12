package com.shaikhsoheb.marvelcharactersapp.di.module

import android.content.Context
import androidx.room.Room
import com.shaikhsoheb.marvelcharactersapp.BuildConfig
import com.shaikhsoheb.marvelcharactersapp.data.local.MarvelCharacterDatabase
import com.shaikhsoheb.marvelcharactersapp.data.remote.RequestInterceptor
import com.shaikhsoheb.marvelcharactersapp.data.remote.api.MarvelCharactersApi
import com.shaikhsoheb.marvelcharactersapp.data.remote.api.MarvelCharactersApiHelper
import com.shaikhsoheb.marvelcharactersapp.data.remote.api.MarvelCharactersApiHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    companion object {
        private const val BASE_URL = "http://gateway.marvel.com/"
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(requestInterceptor: RequestInterceptor) = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MarvelCharactersApi = retrofit.create()

    @Provides
    @Singleton
    fun provideApiHelper(apiHelperImpl: MarvelCharactersApiHelperImpl) = apiHelperImpl

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
            context,
            MarvelCharacterDatabase::class.java,
            "marvel_characters_db"
        ).build()

    @Provides
    @Singleton
    fun provideDao(database: MarvelCharacterDatabase) = database.getMarvelCharacterDao()
}