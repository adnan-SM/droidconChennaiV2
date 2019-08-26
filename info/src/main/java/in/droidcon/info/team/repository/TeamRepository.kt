package `in`.droidcon.info.team.repository

import `in`.droidcon.base.model.GridItem
import io.reactivex.Single

interface TeamRepository {

    fun getTeam(): Single<List<GridItem>>
}