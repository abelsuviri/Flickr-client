package com.abelsuviri.data.service

import com.abelsuviri.data.model.Feed
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

/**
 * @author Abel Suviri
 */

interface FlickrService {
    @GET("photos_public.gne") fun getFeed(): Deferred<Feed>
}