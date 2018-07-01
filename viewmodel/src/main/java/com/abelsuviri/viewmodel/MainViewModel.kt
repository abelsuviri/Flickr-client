package com.abelsuviri.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.abelsuviri.data.model.Feed
import com.abelsuviri.data.repository.FeedRepository
import com.abelsuviri.data.utils.Result
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

/**
 * @author Abel Suviri
 */

class MainViewModel @Inject constructor(private val feedRepository: FeedRepository): ViewModel() {

    val feed: MutableLiveData<Feed> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isFailing: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * Make the request to the service
     */
    fun requestFeed() {
        isLoading.value = true
        launch(CommonPool) {
            val response = feedRepository.getFeed()
            if (response is Result.Success) {
                feed.postValue(response.data)
                isFailing.postValue(false)
            } else if (response is Result.Error) {
                isFailing.postValue(true)
            }

            isLoading.postValue(false)
        }
    }
}