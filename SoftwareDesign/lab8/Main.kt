import clock.NormalClock
import statistic.EventStatisticImpl

fun main() {
    val stat = EventStatisticImpl(NormalClock())
    stat.incEvent("A")
    stat.incEvent("A")
    stat.incEvent("A")
    stat.incEvent("B")
    stat.incEvent("B")
    stat.incEvent("C")
    stat.printStatistic()
}