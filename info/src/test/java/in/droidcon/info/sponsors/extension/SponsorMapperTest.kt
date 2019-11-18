package `in`.droidcon.info.sponsors.extension

import `in`.droidcon.info.mock.FactoryOutlet
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Hari on 2019-08-26.
 * Sponsor mapper test
 */
@RunWith(JUnit4::class)
class SponsorMapperTest {

    @Test
    fun `Test if entity model maps to domain`() {
        val response = FactoryOutlet.makeSponsorsEntity()
        val output = response.mapToDomain()

        assertEquals(response.avatar, output.gridImg)
        assertEquals(response.name, output.gridName)
        assertEquals(response.blob, output.gridBlurbs)
    }
}