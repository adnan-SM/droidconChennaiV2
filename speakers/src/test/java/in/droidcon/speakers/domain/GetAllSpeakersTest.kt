package `in`.droidcon.speakers.domain

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.speakers.mock.FactoryOutlet
import `in`.droidcon.speakers.repository.SpeakerRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Hari on 2019-07-24.
 * Get all speakers test
 */
@RunWith(JUnit4::class)
class GetAllSpeakersTest {

    private val executionThread: ExecutionThread = mock()

    private val speakerRepository: SpeakerRepository = mock()

    private val getSpeakers = GetAllSpeakers(executionThread, speakerRepository)

    @Test
    fun `get speaker list completes`() {
        stubGetSpeakerResponse(Single.just(listOf(FactoryOutlet.makeGridItem())))
        val testObject = getSpeakers.buildUseCase().test()
        verify(speakerRepository, times(1)).getSpeakers()
        testObject.assertComplete()
    }

    @Test
    fun `get speaker list returns right data`() {
        val response = listOf(FactoryOutlet.makeGridItem())
        stubGetSpeakerResponse(Single.just(response))

        getSpeakers.buildUseCase().test().assertValue(response)
    }

    private fun stubGetSpeakerResponse(thisThing: Single<List<GridItem>>) {
        whenever(speakerRepository.getSpeakers()).thenReturn(thisThing)
    }
}