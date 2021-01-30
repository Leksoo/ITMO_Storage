import clock.SetableClock
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import statistic.EventStatistic
import statistic.EventStatisticImpl
import java.time.Instant
import java.util.concurrent.TimeUnit

class EventStatisticTest {

    lateinit var clock: SetableClock
    lateinit var stat: EventStatistic

    @BeforeEach
    fun init() {
        clock = SetableClock()
        stat = EventStatisticImpl(clock)
    }

    @Test
    fun eventByName() {
        clock.now = Instant.ofEpochSecond(0)
        stat.incEvent("A")
        stat.incEvent("A")
        assertEquals(2.0 / 60, stat.getEventStatisticByName("A"), EPS)
        assertEquals(0.0, stat.getEventStatisticByName("B"), EPS)
    }

    @Test
    fun singleEvent() {
        clock.now = Instant.ofEpochSecond(0)
        stat.incEvent("A")
        stat.incEvent("A")
        checkStats(mapOf(Pair("A", 2.0 / 60)))
    }

    @Test
    fun manyEvents() {
        clock.now = Instant.ofEpochSecond(0)
        stat.incEvent("A")
        stat.incEvent("A")
        stat.incEvent("A")
        stat.incEvent("B")
        stat.incEvent("B")
        stat.incEvent("C")
        checkStats(mapOf(Pair("A", 3.0 / 60), Pair("B", 2.0 / 60), Pair("C", 1.0 / 60)))
    }

    @Test
    fun expiredEvent() {
        clock.now = Instant.ofEpochSecond(0)
        stat.incEvent("A")
        clock.now = Instant.ofEpochSecond(SECONDS_IN_HOUR)
        stat.incEvent("A")
        clock.now = Instant.ofEpochSecond(SECONDS_IN_HOUR + 1)
        checkStats(mapOf(Pair("A", 1.0 / 60)))
    }

    @Test
    fun manyExpired() {
        clock.now = Instant.ofEpochSecond(0)
        stat.incEvent("B")
        clock.now = Instant.ofEpochSecond(SECONDS_IN_MINUTE * 10)
        stat.incEvent("A")
        clock.now = Instant.ofEpochSecond(SECONDS_IN_MINUTE * 20)
        stat.incEvent("B")
        stat.incEvent("C")
        clock.now = Instant.ofEpochSecond(SECONDS_IN_MINUTE * 30)
        stat.incEvent("C")
        stat.incEvent("A")
        stat.incEvent("B")
        clock.now = Instant.ofEpochSecond(SECONDS_IN_MINUTE * 80)
        checkStats(mapOf(Pair("A", 1.0 / 60), Pair("B", 1.0 / 60), Pair("C", 1.0 / 60)))
    }


    @Test
    fun allExpired() {
        clock.now = Instant.ofEpochSecond(0)
        stat.incEvent("A")
        clock.now = Instant.ofEpochSecond(SECONDS_IN_MINUTE * 10)
        stat.incEvent("A")
        clock.now = Instant.ofEpochSecond(SECONDS_IN_MINUTE * 20)
        stat.incEvent("B")
        clock.now = Instant.ofEpochSecond(SECONDS_IN_MINUTE * 20)
        stat.incEvent("C")
        clock.now = Instant.ofEpochSecond(SECONDS_IN_HOUR * 4)
        checkStats(mapOf())
    }

    private fun checkStats(expected: Map<String, Double>) {
        val allStats = stat.getAllEventStatistic()
        assertTrue(expected.size == allStats.size)
        for ((k, v) in allStats) {
            assertTrue(expected.containsKey(k))
            assertEquals(expected.getValue(k), v, EPS)
            assertEquals(expected.getValue(k), stat.getEventStatisticByName(k), EPS)

        }
    }

    private companion object {
        val SECONDS_IN_HOUR = TimeUnit.HOURS.toSeconds(1)
        val SECONDS_IN_MINUTE = TimeUnit.MINUTES.toSeconds(1)
        const val EPS = 1e-5
    }
}