package lab2

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import lab2.http.IVkAPI
import lab2.http.VkAPI
import lab2.http.VkAPIRequestException
import lab2.model.FeedItem
import org.hamcrest.CoreMatchers.both
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import java.time.Duration.between
import java.time.Instant
import kotlin.time.minutes

class VkHashtagSearcherTest {
    @Mock
    private val vkProvider: IVkAPI = mock()
    private val vkHashtagSearcher = VkHashtagSearcher(vkProvider)

    @Test
    fun emptyResponse() {
        val hours = 10
        assertResponse(hours, List(hours) { 0 }, emptyList())
    }

    @Test
    fun `last 1 hour`() {
        val response = Instant.now().epochSecond.let {
            listOf(
                FeedItem(it - 30.minutes.inSeconds.toLong())
            )
        }
        val hours = 1
        assertResponse(hours, listOf(1), response)
    }

    @Test
    fun `last 3 hours`() {
        val response = Instant.now().epochSecond.let {
            listOf(
                FeedItem(it - 30.minutes.inSeconds.toLong()),
                FeedItem(it - 30.minutes.inSeconds.toLong()),
                FeedItem(it - 70.minutes.inSeconds.toLong()),
                FeedItem(it - 80.minutes.inSeconds.toLong()),
                FeedItem(it - 140.minutes.inSeconds.toLong())
            )
        }
        val hours = 3
        assertResponse(hours, listOf(2, 2, 1), response)
    }

    @Test
    fun `response with items sooner than start time`() {
        val response = Instant.now().epochSecond.let {
            listOf(
                FeedItem(it - 30.minutes.inSeconds.toLong()),
                FeedItem(it - 30.minutes.inSeconds.toLong()),
                FeedItem(it - 70.minutes.inSeconds.toLong()),
                FeedItem(it - 80.minutes.inSeconds.toLong()),
                FeedItem(it - 140.minutes.inSeconds.toLong())
            )
        }
        val hours = 1
        assertResponse(hours, listOf(2), response)
    }

    @Test
    fun `request error response`() {
        whenever(
            vkProvider.getLastNewsfeedsWithHashtag(
                any(),
                any(),
                any()
            )
        ).thenThrow(VkAPIRequestException(404))
        val hours = 3
        assertThrows<VkAPIRequestException> {
            vkHashtagSearcher.getLastNewsfeeds("#abs", hours)
        }
    }

    @Test
    fun `real request`() {
        val hours = 20
        val res = VkHashtagSearcher(VkAPI()).getLastNewsfeeds("#vk", hours)
        assertEquals(hours, res.size)
        res.forEach {
            assertThat(it, `is`(both(greaterThanOrEqualTo(0)).and(lessThanOrEqualTo(30))))
        }
    }

    private fun assertResponse(hours: Int, expected: List<Int>, response: List<FeedItem>) {
        whenever(
            vkProvider.getLastNewsfeedsWithHashtag(
                any(),
                any(),
                any()
            )
        ).thenReturn(response)
        val res = vkHashtagSearcher.getLastNewsfeeds("#abs", hours)
        assertEquals(expected, res)
    }
}