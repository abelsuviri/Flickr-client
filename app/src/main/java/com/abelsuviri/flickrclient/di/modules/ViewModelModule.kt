package com.abelsuviri.flickrclient.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.abelsuviri.flickrclient.di.scope.ViewModelRetention
import com.abelsuviri.viewmodel.MainViewModel
import com.abelsuviri.viewmodel.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Abel Suviri
 */

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelRetention(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}