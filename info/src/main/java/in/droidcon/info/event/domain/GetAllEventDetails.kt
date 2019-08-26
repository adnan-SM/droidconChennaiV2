package `in`.droidcon.info.event.domain

import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.base.usecase.BasicSingleUseCase
import `in`.droidcon.info.common.model.InfoEntity
import `in`.droidcon.info.event.repository.EventRepository
import io.reactivex.Single

/**
 * Created by Hari on 2019-08-14.
 * Interactors for data channel
 */
class GetAllEventDetails(
    executionThread: ExecutionThread,
    private val eventRepository: EventRepository
) :
    BasicSingleUseCase<List<InfoEntity>>(executionThread) {

    public override fun buildUseCase(): Single<List<InfoEntity>> {
        return eventRepository.getEventDetails()
    }
}