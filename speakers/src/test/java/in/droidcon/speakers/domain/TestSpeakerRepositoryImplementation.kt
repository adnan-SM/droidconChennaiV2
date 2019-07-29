package `in`.droidcon.speakers.domain

import `in`.droidcon.speakers.getSpeakerModelList
import `in`.droidcon.speakers.model.SpeakerEntity
import `in`.droidcon.speakers.remote.SpeakerMapper
import `in`.droidcon.speakers.remote.SpeakerModel
import `in`.droidcon.speakers.remote.SpeakerService
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.describe

class TestSpeakerRepositoryImplementation: Spek({

    val speakerService: SpeakerService by memoized(CachingMode.SCOPE) {
        mock<SpeakerService>()
    }

    val speakerModelList: List<SpeakerModel> by memoized(CachingMode.SCOPE) {
        getSpeakerModelList()
    }

    describe("Test getSpeakers method") {

        context("If speakerService.getSpeakers() returns data, speakerRepo.getSpeakers() should also return data") {
            val speakerListTestObserver = TestObserver<List<SpeakerEntity>>()
            val speakerRepo = SpeakerRepositoryImplementation(speakerService)

            whenever(speakerService.getSpeakers())
                .thenReturn(Single.just(speakerModelList))

            speakerRepo.getSpeakers().subscribe(speakerListTestObserver)

            it("check if speakerService.getSpeakers() is called atleast once") {
                verify(speakerService, atLeastOnce()).getSpeakers()
            }

            it("check if subscribed") {
                speakerListTestObserver.assertSubscribed()
            }

            it("speakerService.getSpeakers() should not emit error") {
                speakerListTestObserver.assertNoErrors()
            }

            it("returned list should be same with speakerService.getSpeakers()") {
                speakerListTestObserver.assertResult(speakerModelList.map(SpeakerMapper::mapFromRemote))
            }
        }

        context("If speakerService.getSpeakers() returns error, speakerRepo.getSpeakers() should also return error") {
            val speakerListTestObserver = TestObserver<List<SpeakerEntity>>()
            val testException = Exception("Test Exception")
            val speakerRepo = SpeakerRepositoryImplementation(speakerService)

            whenever(speakerService.getSpeakers())
                .thenReturn(Single.error(testException))

            speakerRepo.getSpeakers().subscribe(speakerListTestObserver)

            it("check if subscribed") {
                speakerListTestObserver.assertSubscribed()
            }

            it("speakerService.getSpeakers() should emit error") {
                speakerListTestObserver.assertError(testException)
            }
        }
    }

    describe("Test getOneSpeaker method") {

        context("If speakerService.getSpeakers() returns data, speakerRepo.getOneSpeaker() should also return data") {
            val speakerTestObserver = TestObserver<SpeakerEntity>()
            val speakerRepo = SpeakerRepositoryImplementation(speakerService)

            whenever(speakerService.getSpeakers())
                .thenReturn(Single.just(speakerModelList))

            speakerRepo.getOneSpeaker(speakerModelList[0].id).subscribe(speakerTestObserver)

            it("check if speakerService.getSpeakers() is called atleast once") {
                verify(speakerService, atLeastOnce()).getSpeakers()
            }

            it("check if subscribed") {
                speakerTestObserver.assertSubscribed()
            }

            it("speakerService.getSpeakers() should not emit error") {
                speakerTestObserver.assertNoErrors()
            }

            it("returned item should be the speakerModelList[0]") {
                speakerTestObserver.assertResult(SpeakerMapper.mapFromRemote(speakerModelList[0]))
            }
        }
    }
})