package `in`.droidcon.speakers.presentation

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.thread.TestThread
import `in`.droidcon.speakers.domain.GetOneSpeaker
import `in`.droidcon.speakers.mock.FactoryOutlet
import `in`.droidcon.speakers.mock.MockData
import `in`.droidcon.speakers.repository.SpeakerRepository
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

/**
 * Created by Hari on 2019-07-24.
 * Unit test for speaker detail view model
 */
@RunWith(JUnit4::class)
class SpeakerDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var executionThread: TestThread
    private lateinit var speakerRepository: SpeakerRepository
    private lateinit var getSpeaker: GetOneSpeaker

    private lateinit var speakersViewModel: SpeakerDetailViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        executionThread = TestThread()
        speakerRepository = mock()
        getSpeaker = GetOneSpeaker(executionThread, speakerRepository)

        stubSpeakerResponse(Single.just(FactoryOutlet.makeGridItem()))
        speakersViewModel = SpeakerDetailViewModel(getSpeaker)
    }

    @Test
    fun `speakers view model executes interactor`() {
        val speakerId = MockData.randomString()
        speakersViewModel.getOneSpeaker(speakerId)
        verify(speakerRepository, times(1)).getOneSpeaker(speakerId)
    }

    @Test
    fun `speakers interactor executes and completes`() {
        getSpeaker.execute(GetOneSpeaker.Companion.Params(MockData.randomString()))
            .test().assertComplete()
    }

    private fun stubSpeakerResponse(thisThing: Single<GridItem>) {
        whenever(speakerRepository.getOneSpeaker(any())).thenReturn(thisThing)
    }
}