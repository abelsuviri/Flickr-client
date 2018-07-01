package com.abelsuviri.flickrclient.di.components

import com.abelsuviri.flickrclient.FlickrClientApp
import com.abelsuviri.flickrclient.di.modules.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * @author Abel Suviri
 */

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class])
interface AppComponent {
    fun inject(app: FlickrClientApp)
}