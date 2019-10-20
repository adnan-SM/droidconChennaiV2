package `in`.droidcon.schedule.repository

import `in`.droidcon.base.model.SpeakerEntity
import `in`.droidcon.schedule.model.ScheduleEntity
import io.reactivex.Single

/**
 * Created Hari on 2019-10-12.
 * Schedule repository
 */
interface ScheduleRepository {

    fun getDayOneSchedule(): Single<List<ScheduleEntity>>

    fun getDayTwoSchedule(): Single<List<ScheduleEntity>>

    fun querySpeakers(talkId: String): Single<List<SpeakerEntity>>
}