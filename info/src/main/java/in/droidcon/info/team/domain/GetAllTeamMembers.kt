package `in`.droidcon.info.team.domain

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.base.usecase.BasicSingleUseCase
import `in`.droidcon.info.team.repository.TeamRepository
import io.reactivex.Single

class GetAllTeamMembers(executionThread: ExecutionThread, private val teamRepository: TeamRepository):
    BasicSingleUseCase<List<GridItem>>(executionThread) {

    public override fun buildUseCase(): Single<List<GridItem>> {
        return teamRepository.getTeam()
    }
}