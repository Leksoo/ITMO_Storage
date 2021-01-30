package lab2

import lab2.http.IVkAPI
import java.time.Instant
import kotlin.time.hours

class VkHashtagSearcher(val vkAPI: IVkAPI) {


    fun getLastNewsfeeds(hashtag: String, hours: Int): List<Int> {
        val curTimeEpoch = Instant.now().epochSecond
        val startTime = curTimeEpoch - hours.hours.inSeconds.toLong()
        val statistic = MutableList(hours) { 0 }
        val newsfeedItems = vkAPI.getLastNewsfeedsWithHashtag(hashtag, startTime, curTimeEpoch)
        for (item in newsfeedItems) {
            (item.date - startTime).toInt().let {
                if (it > 0) {
                    statistic[hours - 1 - it / HOUR_IN_SECONDS]++
                }
            }
        }
        return statistic
    }

    private companion object {
        val HOUR_IN_SECONDS = 1.hours.inSeconds.toInt()
    }
}