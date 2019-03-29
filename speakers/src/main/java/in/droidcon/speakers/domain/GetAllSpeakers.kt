package `in`.droidcon.speakers.domain

import `in`.droidcon.base.core.BasicSingleUseCase
import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.data.speakers.model.SpeakerEntity
import io.reactivex.Single

class GetAllSpeakers(executionThread: ExecutionThread, val speakerRepository: SpeakerRepository):
    BasicSingleUseCase<List<SpeakerEntity>>(executionThread) {

    public override fun buildUseCase(): Single<List<SpeakerEntity>> {
        return speakerRepository.getSpeakers()
    }
}