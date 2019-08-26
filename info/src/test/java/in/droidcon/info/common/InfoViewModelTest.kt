package `in`.droidcon.info.common

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.thread.TestThread
import `in`.droidcon.info.common.model.InfoEntity
import `in`.droidcon.info.common.presentation.InfoViewModel
import `in`.droidcon.info.event.domain.GetAllEventDetails
import `in`.droidcon.info.event.repository.EventRepository
import `in`.droidcon.info.general.domain.GetAllGeneralDetails
import `in`.droidcon.info.general.repository.GeneralRepository
import `in`.droidcon.info.mock.FactoryOutlet
import `in`.droidcon.info.sponsors.domain.GetAllSponsors
import `in`.droidcon.info.sponsors.repository.SponsorsRepository
import `in`.droidcon.info.team.domain.GetAllTeamMembers
import `in`.droidcon.info.team.repository.TeamRepository
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

/**
 * Created by Hari on 2019-08-18.
 * info viewmodel test
 */
@RunWith(JUnit4::class)
class InfoViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val eventRepository: EventRepository = mock()
    private val teamRepository: TeamRepository = mock()
    private val sponsorsRepository: SponsorsRepository = mock()
    private val generalRepository: GeneralRepository = mock()

    private lateinit var executionThread: TestThread
    private lateinit var getEventDetails: GetAllEventDetails
    private lateinit var getTeam: GetAllTeamMembers
    private lateinit var getAllSponsors: GetAllSponsors
    private lateinit var getAllGeneralDetails: GetAllGeneralDetails

    private lateinit var infoViewModel: InfoViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        executionThread = TestThread()
        getEventDetails = GetAllEventDetails(executionThread, eventRepository)
        getTeam = GetAllTeamMembers(executionThread, teamRepository)
        getAllSponsors = GetAllSponsors(executionThread, sponsorsRepository)
        getAllGeneralDetails = GetAllGeneralDetails(executionThread, generalRepository)

        stubEventDetails(Single.just(listOf(FactoryOutlet.makeInfoEntity())))
        stubGeneralDetails(Single.just(listOf(FactoryOutlet.makeInfoEntity())))
        stubSponsorDetails(Single.just(listOf(FactoryOutlet.makeGridItem())))
        stubTeamDetails(Single.just(listOf(FactoryOutlet.makeGridItem())))

        infoViewModel = InfoViewModel(getEventDetails, getTeam, getAllSponsors, getAllGeneralDetails)
    }

    @Test
    fun `info viewmodel executes interactor`() {
        verify(eventRepository, times(1)).getEventDetails()
        verify(generalRepository, times(1)).getGeneralDetails()
        verify(sponsorsRepository, times(1)).getSponsors()
        verify(teamRepository, times(1)).getTeam()
    }

    @Test
    fun `info completes with interactor`() {
        getTeam.execute().test().assertComplete()
        getAllSponsors.execute().test().assertComplete()
        getEventDetails.execute().test().assertComplete()
        getAllGeneralDetails.execute().test().assertComplete()
    }

    private fun stubEventDetails(thisThing: Single<List<InfoEntity>>) {
        whenever(eventRepository.getEventDetails()).thenReturn(thisThing)
    }

    private fun stubTeamDetails(thisThing: Single<List<GridItem>>) {
        whenever(teamRepository.getTeam()).thenReturn(thisThing)
    }

    private fun stubSponsorDetails(thisThing: Single<List<GridItem>>) {
        whenever(sponsorsRepository.getSponsors()).thenReturn(thisThing)
    }

    private fun stubGeneralDetails(thisThing: Single<List<InfoEntity>>) {
        whenever(generalRepository.getGeneralDetails()).thenReturn(thisThing)
    }
}