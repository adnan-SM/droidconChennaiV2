package `in`.droidcon.info.team.extension

import `in`.droidcon.info.mock.FactoryOutlet
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Hari on 2019-08-18.
 * Team entity maps data to domain
 */
@RunWith(JUnit4::class)
class TeamMapperTest {

    @Test
    fun `Test if entity model is mapped to domain`() {
        val response = FactoryOutlet.makeSpeakerEntity()
        val output = response.mapToDomain()

        assertEquals(response.avatar, output.gridImg)
        assertEquals(response.blob, output.gridBlurbs)
        assertEquals(response.name, output.gridName)
    }
}