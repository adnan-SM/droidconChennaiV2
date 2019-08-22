package `in`.droidcon.speakers.extension

import `in`.droidcon.speakers.mock.FactoryOutlet
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Hari on 2019-07-24.
 * speakers extension tests
 */
@RunWith(JUnit4::class)
class SpeakerMapperTest {

    @Test
    fun `speaker entity maps data to domain`() {
        val entity = FactoryOutlet.makeSpeakerEntity()
        val domain = entity.mapToDomain()

        assertEquals(entity.speakerBlurbs, domain.gridBlurbs)
        assertEquals(entity.speakerName, domain.gridName)
        assertEquals(entity.speakerHandle, domain.gridHandle)
        assertEquals(entity.speakerImg, domain.gridImg)
        assertEquals(entity.speakerLocation, domain.gridLocation)
        assertEquals(entity.speakerOrg, domain.gridOrg)
        assertEquals(entity.speakerOneLiner, domain.gridOneLiner)
        assertEquals(entity.talkId, domain.talkId)
    }

}