package `in`.droidcon.info.sponsors.repository

import `in`.droidcon.base.model.GridItem
import io.reactivex.Single

/**
 * Created by Hari on 2019-08-22.
 * Repository contracts for sponsors
 */
interface SponsorsRepository {

    fun getSponsors(): Single<List<GridItem>>

    fun getOneSponsor(id: String): Single<GridItem>
}