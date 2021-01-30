package statistic

import clock.Clock
import java.time.Duration
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

class EventStatisticImpl(
    private val clock: Clock
) : EventStatistic {

    private val statCountMap = HashMap<String, Int>()
    private val statQueue = LinkedList<Event>()

    override fun incEvent(name: String) {
        removeExpiredEvents()
        statQueue.addLast(Event(name, clock.now()))
        statCountMap.putIfAbsent(name, 0)
        statCountMap[name] = statCountMap[name]!! + 1
    }

    override fun getEventStatisticByName(name: String): Double {
        removeExpiredEvents()
        return statCountMap[name]?.let {
            it.toDouble() / MINUTES_IN_HOUR
        } ?: 0.0
    }

    override fun getAllEventStatistic(): Map<String, Double> {
        removeExpiredEvents()
        return statCountMap.mapValues { (_, v) -> v.toDouble() / MINUTES_IN_HOUR }
    }

    override fun printStatistic() {
        getAllEventStatistic().forEach { (k, v) ->
            println("event: $k, rpm: $v")
        }
    }

    private fun removeExpiredEvents() {
        while (true) {
            val event = statQueue.peekFirst() ?: return
            if (Duration.between(event.time, clock.now()).toHours() >= 1) {
                statQueue.pollFirst()
                statCountMap[event.name]?.let {
                    if (it - 1 == 0) {
                        statCountMap.remove(event.name)
                    } else {
                        statCountMap[event.name] = it - 1
                    }
                }
            } else {
                break
            }
        }
    }

    private data class Event(
        val name: String,
        val time: Instant
    )

    private companion object {
        val MINUTES_IN_HOUR = TimeUnit.HOURS.toMinutes(1)
    }
}