package `in`.droidcon.speakers.presentation

import `in`.droidcon.base.thread.TestThread
import `in`.droidcon.speakers.domain.GetAllSpeakers
import `in`.droidcon.speakers.mock.FactoryOutlet
import `in`.droidcon.speakers.model.SpeakerItem
import `in`.droidcon.speakers.repository.SpeakerRepository
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
 * Created by Hari on 2019-07-24.
 * Unit test for speaker list view model
 */
@RunWith(JUnit4::class)
class SpeakerListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var executionThread: TestThread
    private lateinit var speakerRepository: SpeakerRepository
    private lateinit var getSpeakers: GetAllSpeakers

    private lateinit var speakersViewModel: SpeakerListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        executionThread = TestThread()
        speakerRepository = mock()
        getSpeakers = GetAllSpeakers(executionThread, speakerRepository)

        stubSpeakerResponse(Single.just(listOf(FactoryOutlet.makeSpeakerItem())))
        speakersViewModel = SpeakerListViewModel(getSpeakers)
    }

    @Test
    fun `speakers view model executes interactor`() {
        verify(speakerRepository, times(1)).getSpeakers()
    }

    @Test
    fun `speakers interactor executes and completes`() {
        getSpeakers.execute().test().assertComplete()
    }

    private fun stubSpeakerResponse(thisThing: Single<List<SpeakerItem>>) {
        whenever(speakerRepository.getSpeakers()).thenReturn(thisThing)
    }
}