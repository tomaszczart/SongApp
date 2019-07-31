package com.tooploox.songapp.dagger

import com.tooploox.songapp.application.SongsApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityBuilderModule::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: SongsApp): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: SongsApp)
}