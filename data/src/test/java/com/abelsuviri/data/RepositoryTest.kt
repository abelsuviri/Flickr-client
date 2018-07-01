package com.abelsuviri.data

import com.abelsuviri.data.mock.MockXml
import com.abelsuviri.data.model.Feed
import com.abelsuviri.data.repository.FeedRepository
import com.abelsuviri.data.utils.Result
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.simpleframework.xml.core.Persister
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * @author Abel Suviri
 */

class RepositoryTest {
    @Mock
    lateinit var flickrRepository: FeedRepository

    lateinit var feed: Result<Feed>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test_get_feed_behaves_correct() {
        val serializer = Persister()
        SimpleXmlConverterFactory.createNonStrict(serializer)
        feed = Result.Success(serializer.read(Feed::class.java, MockXml.mockXml))

        Mockito.`when`(runBlocking { flickrRepository.getFeed() }).thenReturn(feed)
        val response = runBlocking{ flickrRepository.getFeed() }
        Assert.assertEquals(response, feed)
    }

    @Test
    fun test_get_feed_behaves_incorrect() {
        feed = Result.Error(Throwable())

        Mockito.`when`(runBlocking { flickrRepository.getFeed() }).thenReturn(feed)
        val response = runBlocking{ flickrRepository.getFeed() }
        Assert.assertEquals(response, feed)
    }
}