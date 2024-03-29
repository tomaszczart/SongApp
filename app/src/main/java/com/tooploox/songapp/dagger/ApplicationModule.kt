package com.tooploox.songapp.dagger

import android.app.Application
import android.content.Context
import com.tooploox.songapp.application.SongApp
import com.tooploox.songapp.songsScreen.dagger.SongsActivityComponent
import dagger.Module
import dagger.Provides

@Module(subcomponents = [SongsActivityComponent::class])
class ApplicationModule {

    @Provides
    @ApplicationScope
    fun provideContext(application: SongApp): Context = application.applicationContext

    @Provides
    @ApplicationScope
    fun provideApplication(application: SongApp): Application = application

}