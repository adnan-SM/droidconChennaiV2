package `in`.droidcon.speakers.domain

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.base.usecase.BasicSingleUseCase
import `in`.droidcon.speakers.repository.SpeakerRepository
import io.reactivex.Single

class GetAllSpeakers(executionThread: ExecutionThread, val speakerRepository: SpeakerRepository):
    BasicSingleUseCase<List<GridItem>>(executionThread) {

    public override fun buildUseCase(): Single<List<GridItem>> {
        return speakerRepository.getSpeakers()
    }
}