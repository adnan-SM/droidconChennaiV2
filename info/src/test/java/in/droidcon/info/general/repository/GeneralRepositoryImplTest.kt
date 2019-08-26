package `in`.droidcon.info.general.repository

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
 * General repository test
 */
@RunWith(JUnit4::class)
class GeneralRepositoryImplTest {

    private val generalRepository: GeneralRepository = mock()

    @Test
    fun `get event details completes`() {
        stubTeamResponse(Single.just(listOf(FactoryOutlet.makeInfoEntity())))
        generalRepository.getGeneralDetails().test().assertComplete()
    }

    @Test
    fun `get event details returns data`() {
        val response = FactoryOutlet.makeInfoEntity()
        stubTeamResponse(Single.just(listOf(response)))
        generalRepository.getGeneralDetails().test().assertValue(listOf(response))
    }

    private fun stubTeamResponse(thisThing: Single<List<InfoEntity>>) {
        whenever(generalRepository.getGeneralDetails()).thenReturn(thisThing)
    }
}