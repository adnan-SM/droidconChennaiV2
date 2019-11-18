package `in`.droidcon.speakers.domain

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.speakers.mock.FactoryOutlet
import `in`.droidcon.speakers.mock.MockData
import `in`.droidcon.speakers.repository.SpeakerRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
/**
 * Created by Hari on 2019-07-24.
 * Get one speaker unit test
 */
@RunWith(JUnit4::class)
class GetOneSpeakerTest {

    private val executionThread: ExecutionThread = mock()

    private val speakerRepository: SpeakerRepository = mock()

    private val getOneSpeaker =
        GetOneSpeaker(executionThread, speakerRepository)

    @Test
    fun `get one speaker completes`() {
        stubGetSpeakerResponse(Single.just(FactoryOutlet.makeGridItem()))
        getOneSpeaker.buildUseCase(GetOneSpeaker.Companion.Params(MockData.randomString())).test().assertComplete()
    }

    @Test
    fun `get one speaker returns values`() {
        val response = FactoryOutlet.makeGridItem()
        stubGetSpeakerResponse(Single.just(response))
        getOneSpeaker.buildUseCase(GetOneSpeaker.Companion.Params(MockData.randomString()))
            .test().assertValue(response)
    }

    private fun stubGetSpeakerResponse(thisThing: Single<GridItem>) {
        whenever(speakerRepository.getOneSpeaker(any())).thenReturn(thisThing)
    }
}