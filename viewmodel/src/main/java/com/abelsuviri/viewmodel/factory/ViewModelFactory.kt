package com.abelsuviri.viewmodel.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * @author Abel Suviri
 */

@Singleton
class ViewModelFactory @Inject constructor(private val creators: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = creators[modelClass]?.get() as T
}