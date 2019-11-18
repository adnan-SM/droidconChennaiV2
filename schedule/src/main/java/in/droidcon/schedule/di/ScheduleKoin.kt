package `in`.droidcon.schedule.di

import org.koin.standalone.StandAloneContext.loadKoinModules

/**
 * Created by Hari on 2019-10-12.
 * Koin module
 */
object ScheduleKoin {

    fun init() = loadKoinModules(scheduleModule)
}