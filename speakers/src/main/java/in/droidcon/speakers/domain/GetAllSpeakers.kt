package `in`.droidcon.speakers.domain

import `in`.droidcon.base.core.BasicSingleUseCase
import `in`.droidcon.speakers.ExecutionThread
import `in`.droidcon.speakers.model.SpeakerItem
import io.reactivex.Single

class GetAllSpeakers(executionThread: ExecutionThread, val speakerRepository: SpeakerRepository):
    BasicSingleUseCase<List<SpeakerItem>>(executionThread) {

    public override fun buildUseCase(): Single<List<SpeakerItem>> {
        return speakerRepository.getSpeakers()
    }
}