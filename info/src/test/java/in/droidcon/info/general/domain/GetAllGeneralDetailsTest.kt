package `in`.droidcon.info.general.domain

import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.info.common.model.InfoEntity
import `in`.droidcon.info.general.repository.GeneralRepository
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
 * Created by Hari on 2019-08-26.
 * Get all general details test
 */
@RunWith(JUnit4::class)
class GetAllGeneralDetailsTest {

    private val executionThread: ExecutionThread = mock()
    private val generalRepository: GeneralRepository = mock()
    private val getAllGeneralDetails = GetAllGeneralDetails(executionThread, generalRepository)

    @Test
    fun `Test get all general details executes`() {
        stubGetAllGeneralDetailsResponse(Single.just(listOf(FactoryOutlet.makeInfoEntity())))
        val usecase = getAllGeneralDetails.buildUseCase().test()
        verify(generalRepository, times(1)).getGeneralDetails()
        usecase.assertComplete()
    }

    @Test
    fun `Test get all general details returns data`() {
        val response = listOf(FactoryOutlet.makeInfoEntity())
        stubGetAllGeneralDetailsResponse(Single.just(response))
        getAllGeneralDetails.buildUseCase().test().assertValue(response)
    }

    private fun stubGetAllGeneralDetailsResponse(thisThing: Single<List<InfoEntity>>) {
        whenever(generalRepository.getGeneralDetails()).thenReturn(thisThing)
    }
}