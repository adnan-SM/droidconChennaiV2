package `in`.droidcon.info.sponsors.domain

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.info.mock.FactoryOutlet
import `in`.droidcon.info.sponsors.domain.GetAllSponsors
import `in`.droidcon.info.sponsors.repository.SponsorsRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Hari B.V on 2019-08-26.
 * Interactors test
 */
@RunWith(JUnit4::class)
class GetAllSponsorsTest {

    private val executionThread: ExecutionThread = mock()
    private val sponsorsRepository: SponsorsRepository = mock()
    private val getAllSponsors = GetAllSponsors(executionThread, sponsorsRepository)

    @Test
    fun `get all sponsros completes and calls repository`() {
        stubGetAllSponsors(Single.just(listOf(FactoryOutlet.makeGridItem())))
        val testUsecase = getAllSponsors.buildUseCase().test()
        verify(sponsorsRepository, times(1)).getSponsors()
        testUsecase.assertComplete()
    }

    @Test
    fun `get all sponsros returns data`() {
        val response = listOf(FactoryOutlet.makeGridItem())
        stubGetAllSponsors(Single.just(response))
        getAllSponsors.buildUseCase().test().assertValue(response)
    }

    private fun stubGetAllSponsors(thisThing: Single<List<GridItem>>) {
        whenever(sponsorsRepository.getSponsors()).thenReturn(thisThing)
    }
}