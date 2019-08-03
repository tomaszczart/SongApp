package com.tooploox.songapp.dagger

import com.tooploox.songapp.application.SongApp
import com.tooploox.songapp.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityBuilderModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: SongApp): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: SongApp)
}