package lab1

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.random.Random

class LRUCacheTest {
    private lateinit var cache: LRUCache<Int, Int>

    @BeforeEach
    fun refreshCache() {
        cache = LRUCache(6)
    }

    @Test
    fun largeRandom() {
        val bigCache = LRUCache<Int, Int>(100)
        val ops = listOf(
            { bigCache.put(Random.nextInt(100), Random.nextInt(100)) },
            { bigCache.get(Random.nextInt(100)) },
            { bigCache.remove(Random.nextInt(100)) }
        )
        for (i in 0..1_000_000) {
            ops.random().invoke()
        }
    }

    @Test
    fun putGetMatch() {
        cache.apply {
            put(1, 10)
            put(2, 20)
            assertEquals(10, get(1))
            assertEquals(20, get(2))
            put(1, 30)
            assertEquals(30, get(1))
        }
    }

    @Test
    fun overflow() {
        fillCache()
        cache.apply {
            put(6, 60)
            assertEquals(null, get(0))
            assertEquals(60, get(6))
            put(7, 70)
            assertEquals(null, get(0))
            assertEquals(null, get(1))
        }
    }

    @Test
    fun overflowWithPriority() {
        fillCache()
        cache.apply {
            assertEquals(0, get(0))
            put(6, 60)
            assertEquals(null, get(1))
            assertEquals(0, get(0))
        }
    }

    @Test
    fun clear() {
        fillCache()
        cache.apply {
            clear()
            assertEquals(0, size)
            repeat(6) {
                assertEquals(null, get(it))
            }
        }
    }

    @Test
    fun remove() {
        fillCache()
        cache.apply {
            assertEquals(0, remove(0))
            assertEquals(null, get(0))
            put(6, 60)
            assertEquals(60, get(6))
        }
    }

    private fun fillCache() {
        repeat(6) {
            cache.put(it, it * 10)
        }
    }
}