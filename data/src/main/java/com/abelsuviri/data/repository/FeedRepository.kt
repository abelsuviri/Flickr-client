package com.abelsuviri.data.repository

import com.abelsuviri.data.model.Feed
import com.abelsuviri.data.service.FlickrService
import com.abelsuviri.data.utils.Result
import javax.inject.Inject

/**
 * @author Abel Suviri
 */

class FeedRepository @Inject constructor(private val flickrService: FlickrService) {
    /**
     * @return the result of the network request to the service
     */
    suspend fun getFeed(): Result<Feed> {
        return try {
            val response = flickrService.getFeed()
            val result = response.await()
            Result.Success(result)
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }
}