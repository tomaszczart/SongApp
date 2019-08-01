package com.tooploox.songapp.songsScreen.dagger

import com.tooploox.songapp.songsScreen.SongsActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@SongsActivityScope
@Subcomponent(modules = [SongsActivityModule::class])
interface SongsActivityComponent : AndroidInjector<SongsActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SongsActivity>()
}