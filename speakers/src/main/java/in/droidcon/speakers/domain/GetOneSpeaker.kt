package `in`.droidcon.speakers.domain

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.base.usecase.SingleUseCase
import `in`.droidcon.speakers.repository.SpeakerRepository
import io.reactivex.Single

/**
 * Created by Hari on 2019-07-23.
 */

class GetOneSpeaker(
    executionThread: ExecutionThread,
    private val speakerRepository: SpeakerRepository
): SingleUseCase<GridItem, GetOneSpeaker.Companion.Params>(executionThread) {

    public override fun buildUseCase(params: Params): Single<GridItem> {
        return speakerRepository.getOneSpeaker(params.speakerId)
    }

    companion object {
        data class Params(val speakerId: String)
    }
}