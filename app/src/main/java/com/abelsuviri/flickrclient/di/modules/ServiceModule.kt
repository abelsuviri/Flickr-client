package com.abelsuviri.flickrclient.di.modules

import com.abelsuviri.data.service.FlickrService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

/**
 * @author Abel Suviri
 */

@Module
class ServiceModule {

    private val baseUrl = "https://api.flickr.com/services/feeds/"

    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy())))
                .build()
    }

    @Provides
    @Singleton
    fun flickrService(retrofit: Retrofit): FlickrService {
        return retrofit.create(FlickrService::class.java)
    }
}