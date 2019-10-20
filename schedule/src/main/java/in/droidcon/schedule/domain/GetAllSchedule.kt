package `in`.droidcon.schedule.domain

import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.base.usecase.BasicSingleUseCase
import `in`.droidcon.schedule.model.ScheduleEntity
import `in`.droidcon.schedule.repository.ScheduleRepository
import io.reactivex.Single

/**
 * Created by Hari on 2019-10-12.
 * Get all schedules
 */
class GetAllSchedule(executionThread: ExecutionThread, val scheduleRepository: ScheduleRepository):
    BasicSingleUseCase<List<ScheduleEntity>>(executionThread){

    override fun buildUseCase(): Single<List<ScheduleEntity>> {
        return scheduleRepository.getDayOneSchedule()
    }
}