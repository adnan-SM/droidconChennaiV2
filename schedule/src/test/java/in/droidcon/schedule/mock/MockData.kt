package `in`.droidcon.schedule.mock

import java.math.BigDecimal
import java.util.*
import java.util.concurrent.ThreadLocalRandom
/**
 * Created by Hari on 2019-07-24.
 * Mock data for test
 */

object MockData {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }

    fun randomLong(): Long {
        return randomInt().toLong()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun randomBigDecimal(): BigDecimal {
        return randomInt().toBigDecimal()
    }
}