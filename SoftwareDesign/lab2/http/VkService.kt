package lab2.http

import lab2.model.FeedItemResponse
import lab2.resources.VK_API_TOKEN
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VkService {

    @GET("newsfeed.search?access_token=${VK_API_TOKEN}&v=5.124")
    fun getMatchedNewsfeeds(
        @Query("q") searchingMatch: String,
        @Query("start_time") startTime: Long,
        @Query("end_time") endTime: Long
    ): Call<FeedItemResponse>
}