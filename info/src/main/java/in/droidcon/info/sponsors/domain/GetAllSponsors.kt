package `in`.droidcon.info.sponsors.domain

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.base.usecase.BasicSingleUseCase
import `in`.droidcon.info.sponsors.repository.SponsorsRepository
import io.reactivex.Single

/**
 * Created by Hari on 2019-08-22.
 * Get all sponsors interactors
 */
class GetAllSponsors(executionThread: ExecutionThread, private val sponsorsRepository: SponsorsRepository):
    BasicSingleUseCase<List<GridItem>>(executionThread) {

    public override fun buildUseCase(): Single<List<GridItem>> {
        return sponsorsRepository.getSponsors()
    }
}