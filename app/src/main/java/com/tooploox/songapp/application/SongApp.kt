package com.tooploox.songapp.application

import android.app.Activity
import android.app.Application
import com.tooploox.songapp.BuildConfig
import com.tooploox.songapp.dagger.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject


class SongApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

        //Init Timber when building as debug
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

    }
}