package com.tooploox.songapp.dagger

import com.tooploox.songapp.songsScreen.SongsActivity
import com.tooploox.songapp.songsScreen.dagger.SongsActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @SongsActivityScope
    @ContributesAndroidInjector
    abstract fun contributeSongsActivityInjector(): SongsActivity

}