package `in`.droidcon.schedule.extension

import `in`.droidcon.schedule.model.ScheduleEntity
import `in`.droidcon.schedule.model.ScheduleResponse

/**
 * Created by Hari on 2019-10-12.
 * Mapper for schedule
 */
fun ScheduleResponse.mapToEntity(): ScheduleEntity {
    return ScheduleEntity(
        this.time
    )
}