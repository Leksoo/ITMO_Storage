package lab2.http

import lab2.model.FeedItem
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.RuntimeException

class VkAPI(
    private val baseUrl: HttpUrl = HttpUrl.get("https://api.vk.com/method/")
) : IVkAPI {
    private val vkRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val vkService by lazy {
        vkRetrofit.create(VkService::class.java)
    }

    override fun getLastNewsfeedsWithHashtag(hashtag: String, startTime: Long, endTime: Long): List<FeedItem> {
        val response = vkService.getMatchedNewsfeeds(
            hashtag,
            startTime,
            endTime
        ).execute()
        if (!response.isSuccessful) {
            throw VkAPIRequestException(response.code())
        }
        return response.body()?.feedItems?.items ?: throw VkAPIParserException()
    }
}

class VkAPIRequestException(code: Int) : RuntimeException(code.toString())
class VkAPIParserException : RuntimeException("failed to get desired object from response")