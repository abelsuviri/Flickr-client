package com.abelsuviri.flickrclient.di.scope

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * @author Abel Suviri
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelRetention(val value: KClass<out ViewModel>)