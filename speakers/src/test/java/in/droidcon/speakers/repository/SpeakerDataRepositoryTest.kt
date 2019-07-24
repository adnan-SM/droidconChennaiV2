package `in`.droidcon.speakers.repository

import `in`.droidcon.speakers.mock.FactoryOutlet
import `in`.droidcon.speakers.mock.MockData
import `in`.droidcon.speakers.model.SpeakerItem
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
/**
 * Created by Hari on 2019-07-24.
 * Speaker data repository test
 */
@RunWith(JUnit4::class)
class SpeakerDataRepositoryTest {

    private val speakerDataRepository: SpeakerRepository = mock()

    @Test
    fun `get speakers completes`() {
        stubSpeakerResponse(Single.just(listOf(FactoryOutlet.makeSpeakerItem())))
        speakerDataRepository.getSpeakers().test().assertComplete()
    }

    @Test
    fun `get speakers returns data`() {
        val response = listOf(FactoryOutlet.makeSpeakerItem())
        stubSpeakerResponse(Single.just(response))
        speakerDataRepository.getSpeakers().test().assertValue(response)
    }

    @Test
    fun `get one speaker completes`() {
        stubSpeakerEntityResponse(Single.just(FactoryOutlet.makeSpeakerItem()))
        speakerDataRepository.getOneSpeaker(MockData.randomString()).test().assertComplete()
    }

    @Test
    fun `get one speaker returns data`() {
        val response = FactoryOutlet.makeSpeakerItem()
        stubSpeakerEntityResponse(Single.just(response))
        speakerDataRepository.getOneSpeaker(MockData.randomString()).test().assertValue(response)
    }

    private fun stubSpeakerResponse(thisThing: Single<List<SpeakerItem>>) {
        whenever(speakerDataRepository.getSpeakers()).thenReturn(thisThing)
    }

    private fun stubSpeakerEntityResponse(thisThing: Single<SpeakerItem>) {
        whenever(speakerDataRepository.getOneSpeaker(any())).thenReturn(thisThing)
    }
}