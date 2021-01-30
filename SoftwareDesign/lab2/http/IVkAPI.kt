package lab2.http

import lab2.model.FeedItem

interface IVkAPI {
    fun getLastNewsfeedsWithHashtag(hashtag: String, startTime: Long, endTime: Long): List<FeedItem>
}