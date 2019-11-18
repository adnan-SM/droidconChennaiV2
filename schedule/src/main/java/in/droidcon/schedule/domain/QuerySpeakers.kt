package `in`.droidcon.schedule.domain

import `in`.droidcon.base.model.SpeakerEntity
import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.base.usecase.SingleUseCase
import `in`.droidcon.schedule.repository.ScheduleRepository
import io.reactivex.Single

/**
 * Created by Hari on 2019-10-20.
 * query speakers interactor
 */
class QuerySpeakers(executionThread: ExecutionThread, private val scheduleRepository: ScheduleRepository):
 SingleUseCase<List<SpeakerEntity>, QuerySpeakers.Companion.Params>(executionThread) {

    override fun buildUseCase(params: Params): Single<List<SpeakerEntity>> {
        return scheduleRepository.querySpeakers(params.talkId)
    }

    companion object {
        data class Params(val talkId: String)
    }
}