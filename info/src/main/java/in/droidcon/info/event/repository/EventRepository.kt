package `in`.droidcon.info.event.repository

import `in`.droidcon.info.common.model.EventEntity
import io.reactivex.Single

/**
 * Created by Hari on 2019-08-14.
 * Repository for event details
 */
interface EventRepository {

    fun getRepositoryDetails(): Single<List<EventEntity>>
}