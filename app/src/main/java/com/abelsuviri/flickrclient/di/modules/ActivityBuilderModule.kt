package com.abelsuviri.flickrclient.di.modules

import com.abelsuviri.flickrclient.ui.activities.MainActivity
import com.abelsuviri.flickrclient.ui.activities.PictureDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Abel Suviri
 */

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun pictureDetailActivity(): PictureDetailActivity
}