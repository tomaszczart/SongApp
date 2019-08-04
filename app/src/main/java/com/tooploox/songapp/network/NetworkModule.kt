package com.tooploox.songapp.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tooploox.songapp.dagger.ApplicationScope
import com.tooploox.songapp.network.databases.online.AppleiTunesService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    @ApplicationScope
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl("https://itunes.apple.com/")
            .build()
    }

    @Provides
    @ApplicationScope
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @ApplicationScope
    fun provideAppleiTunesApiService(retrofit: Retrofit): AppleiTunesService =
        retrofit.create(AppleiTunesService::class.java)

}