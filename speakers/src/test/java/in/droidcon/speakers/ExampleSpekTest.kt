package `in`.droidcon.speakers

import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object ExampleSpekTest : Spek({
    describe("Doing something") {
        it("Should pass") {
            assertEquals(expected = 1, actual = 1)
        }
        it("Should fail") {
            assertEquals(expected = 1, actual = 2)
        }
    }
})