package `in`.droidcon.info.event.domain

import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.info.common.model.InfoEntity
import `in`.droidcon.info.event.repository.EventRepository
import `in`.droidcon.info.mock.FactoryOutlet
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Hari on 2019-08-18.
 * Ineractor test
 */
@RunWith(JUnit4::class)
class GetAllEventDetailsTest {

    private val executionThread: ExecutionThread = mock()
    private val eventRepository: EventRepository = mock()
    private val getAllEventDetails = GetAllEventDetails(executionThread, eventRepository)

    @Test
    fun `Test get all event details executes`() {
        stubGetAllEventResponse(Single.just(listOf(FactoryOutlet.makeInfoEntity())))
        val usecase = getAllEventDetails.buildUseCase().test()
        verify(eventRepository, times(1)).getEventDetails()
        usecase.assertComplete()
    }

    @Test
    fun `Test get all event details returns data`() {
        val response = listOf(FactoryOutlet.makeInfoEntity())
        stubGetAllEventResponse(Single.just(response))
        getAllEventDetails.buildUseCase().test().assertValue(response)
    }

    private fun stubGetAllEventResponse(thisThing: Single<List<InfoEntity>>) {
        whenever(eventRepository.getEventDetails()).thenReturn(thisThing)
    }
}