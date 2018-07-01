package com.abelsuviri.flickrclient

import android.app.Activity
import android.app.Application
import com.abelsuviri.flickrclient.di.components.AppComponent
import com.abelsuviri.flickrclient.di.components.DaggerAppComponent
import com.abelsuviri.flickrclient.di.modules.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * @author Abel Suviri
 */

class FlickrClientApp: Application(), HasActivityInjector {
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}