package `in`.droidcon.info.event.repository

import `in`.droidcon.info.common.model.InfoEntity
import `in`.droidcon.info.mock.FactoryOutlet
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Hari on 2019-08-26.
 * Event repository test
 */
@RunWith(JUnit4::class)
class EventRepositoryImplTest {

    private val eventRepository: EventRepository = mock()

    @Test
    fun `get event details completes`() {
        stubTeamResponse(Single.just(listOf(FactoryOutlet.makeInfoEntity())))
        eventRepository.getEventDetails().test().assertComplete()
    }

    @Test
    fun `get event details returns data`() {
        val response = FactoryOutlet.makeInfoEntity()
        stubTeamResponse(Single.just(listOf(response)))
        eventRepository.getEventDetails().test().assertValue(listOf(response))
    }

    private fun stubTeamResponse(thisThing: Single<List<InfoEntity>>) {
        whenever(eventRepository.getEventDetails()).thenReturn(thisThing)
    }
}