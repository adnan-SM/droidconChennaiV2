package `in`.droidcon.info.team.domain

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.base.usecase.SingleUseCase
import `in`.droidcon.info.team.repository.TeamRepository
import io.reactivex.Single

/**
 * Created by Hari on 2019-07-23.
 */

class GetOneTeamMember(
    executionThread: ExecutionThread,
    private val teamRepository: TeamRepository
): SingleUseCase<GridItem, GetOneTeamMember.Companion.Params>(executionThread) {

    public override fun buildUseCase(params: Params): Single<GridItem> {
        return teamRepository.getOneTeamMember(params.speakerId)
    }

    companion object {
        data class Params(val speakerId: String)
    }
}