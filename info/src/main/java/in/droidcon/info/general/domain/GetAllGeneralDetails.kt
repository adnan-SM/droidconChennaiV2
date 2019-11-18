package `in`.droidcon.info.general.domain

import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.base.usecase.BasicSingleUseCase
import `in`.droidcon.info.common.model.InfoEntity
import `in`.droidcon.info.general.repository.GeneralRepository
import io.reactivex.Single

/**
 * Created by Hari on 2019-08-24.
 * interactor for getting all general details
 */
class GetAllGeneralDetails(
    executionThread: ExecutionThread,
    private val generalRepository: GeneralRepository
) :
    BasicSingleUseCase<List<InfoEntity>>(executionThread) {

    public override fun buildUseCase(): Single<List<InfoEntity>> {
        return generalRepository.getGeneralDetails()
    }
}