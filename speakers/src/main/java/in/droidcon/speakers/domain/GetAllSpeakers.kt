package `in`.droidcon.speakers.domain

import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.base.usecase.BasicSingleUseCase
import `in`.droidcon.speakers.repository.SpeakerRepository
import `in`.droidcon.speakers.model.SpeakerItem
import io.reactivex.Single

class GetAllSpeakers(executionThread: ExecutionThread, val speakerRepository: SpeakerRepository):
    BasicSingleUseCase<List<SpeakerItem>>(executionThread) {

    public override fun buildUseCase(): Single<List<SpeakerItem>> {
        return speakerRepository.getSpeakers()
    }
}