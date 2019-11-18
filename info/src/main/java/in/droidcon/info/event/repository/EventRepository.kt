package `in`.droidcon.info.event.repository

import `in`.droidcon.info.common.model.InfoEntity
import io.reactivex.Single

/**
 * Created by Hari on 2019-08-14.
 * Repository for event details
 */
interface EventRepository {

    fun getEventDetails(): Single<List<InfoEntity>>
}