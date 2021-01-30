package clock

import java.time.Instant

class SetableClock(
    var now: Instant = Instant.now()
) : Clock {
    override fun now() = now
}