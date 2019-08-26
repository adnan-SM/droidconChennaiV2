package `in`.droidcon.info.sponsors.repository

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.info.mock.FactoryOutlet
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Hari on 2019-08-26.
 * Sponsor repository implementation test
 */
@RunWith(JUnit4::class)
class SponsorRepositoryImplTest {

    private val sponsorRepository: SponsorsRepository = mock()

    @Test
    fun `get sponsor details completes`() {
        stubTeamResponse(Single.just(listOf(FactoryOutlet.makeGridItem())))
        sponsorRepository.getSponsors().test().assertComplete()
    }

    @Test
    fun `get sponsor details returns data`() {
        val response = FactoryOutlet.makeGridItem()
        stubTeamResponse(Single.just(listOf(response)))
        sponsorRepository.getSponsors().test().assertValue(listOf(response))
    }

    private fun stubTeamResponse(thisThing: Single<List<GridItem>>) {
        whenever(sponsorRepository.getSponsors()).thenReturn(thisThing)
    }
}