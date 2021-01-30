package lab2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FeedItemResponse(

    @field:SerializedName("response")
    val feedItems: FeedItems? = null
)

data class FeedItems(

    @field:SerializedName("items")
    val items: List<FeedItem>? = null
)

data class FeedItem(

    @field:SerializedName("date")
    val date: Long = 0
)
