package lab2.http

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.*
import java.io.File
import java.net.HttpURLConnection
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VkAPIStubTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var vkAPI: IVkAPI

    @BeforeAll
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        vkAPI = VkAPI(mockWebServer.url("/"))
    }

    @AfterAll
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `bad request`() {
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(readJson("bad_request"))
        mockWebServer.enqueue(mockResponse)
        assertThrows<VkAPIRequestException> { vkAPI.getLastNewsfeedsWithHashtag("#1", 1, 1) }
    }

    @Test
    fun `empty response`() {
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(readJson("empty_response"))
        mockWebServer.enqueue(mockResponse)
        assertEquals(0, vkAPI.getLastNewsfeedsWithHashtag("", 1, 1).size)
    }

    @Test
    fun `bad response`() {
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(readJson("bad_response"))
        mockWebServer.enqueue(mockResponse)
        assertThrows<VkAPIParserException> { vkAPI.getLastNewsfeedsWithHashtag("#1", 1, 1) }
    }

    @Test
    fun `correct response`() {
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(readJson("correct_response"))
        mockWebServer.enqueue(mockResponse)
        assertEquals(2, vkAPI.getLastNewsfeedsWithHashtag("", 1, 1).size)
    }

    private fun readJson(fileName: String): String {
        return File("src/test/resources/${fileName}.json").readText()
    }
}
