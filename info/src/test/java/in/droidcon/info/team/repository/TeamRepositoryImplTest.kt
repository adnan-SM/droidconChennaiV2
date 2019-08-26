package `in`.droidcon.info.team.repository

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
 * Team repository test
 */
@RunWith(JUnit4::class)
class TeamRepositoryImplTest {

    private val teamRepository: TeamRepository = mock()

    @Test
    fun `get team details completes`() {
        stubTeamResponse(Single.just(listOf(FactoryOutlet.makeGridItem())))
        teamRepository.getTeam().test().assertComplete()
    }

    @Test
    fun `get team details returns data`() {
        val response = FactoryOutlet.makeGridItem()
        stubTeamResponse(Single.just(listOf(response)))
        teamRepository.getTeam().test().assertValue(listOf(response))
    }

    private fun stubTeamResponse(thisThing: Single<List<GridItem>>) {
        whenever(teamRepository.getTeam()).thenReturn(thisThing)
    }
}