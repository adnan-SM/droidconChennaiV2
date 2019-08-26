package `in`.droidcon.info.team.domain

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.info.team.repository.TeamRepository
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
 * Get all members interactor test
 */
@RunWith(JUnit4::class)
class GetAllTeamMembersTest {

    private val executionThread: ExecutionThread = mock()
    private val teamRepository: TeamRepository = mock()
    private val getAllTeamMembers = GetAllTeamMembers(executionThread, teamRepository)

    @Test
    fun `get all team member completes and calls repository`() {
        stubGetAllTeamResponse(Single.just(listOf(FactoryOutlet.makeGridItem())))
        val testUsecase = getAllTeamMembers.buildUseCase().test()
        verify(teamRepository, times(1)).getTeam()
        testUsecase.assertComplete()
    }

    @Test
    fun `get all team members return right data`() {
        val response = listOf(FactoryOutlet.makeGridItem())
        stubGetAllTeamResponse(Single.just(response))
        getAllTeamMembers.buildUseCase().test().assertValue(response)
    }

    private fun stubGetAllTeamResponse(thisThing: Single<List<GridItem>>) {
        whenever(teamRepository.getTeam()).thenReturn(thisThing)
    }

}