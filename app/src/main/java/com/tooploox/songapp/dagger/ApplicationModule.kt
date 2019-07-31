package com.tooploox.songapp.dagger

import android.content.Context
import com.tooploox.songapp.application.SongsApp
import com.tooploox.songapp.songsScreen.dagger.SongsActivityComponent
import dagger.Module
import dagger.Provides

@Module(subcomponents = [SongsActivityComponent::class])
class ApplicationModule {

    @Provides
    @ApplicationScope
    fun provideContext(application: SongsApp): Context {
        return application.applicationContext
    }

}