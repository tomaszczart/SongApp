package com.tooploox.songapp.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tooploox.songapp.dagger.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun gson(): Gson = GsonBuilder().create()

}