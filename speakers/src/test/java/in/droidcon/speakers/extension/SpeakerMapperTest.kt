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

        assertEquals(entity.speakerBlurbs, domain.speakerBlurbs)
        assertEquals(entity.speakerName, domain.speakerName)
        assertEquals(entity.speakerHandle, domain.speakerHandle)
        assertEquals(entity.speakerImg, domain.speakerImg)
        assertEquals(entity.speakerLocation, domain.speakerLocation)
        assertEquals(entity.speakerOrg, domain.speakerOrg)
        assertEquals(entity.speakerOneLiner, domain.speakerOneLiner)
        assertEquals(entity.talkId, domain.talkId)
    }

}