package `in`.droidcon.info.common.di

import `in`.droidcon.info.event.di.eventModule
import `in`.droidcon.info.sponsors.di.sponsorsModule
import `in`.droidcon.info.team.di.teamModule
import org.koin.standalone.StandAloneContext

/**
 * Created by Hari B.V on 2019-08-16.
 * Koin module handler for info module
 */
object InfoKoin {

    fun init() = StandAloneContext.loadKoinModules(
            infoModule,
            eventModule,
            teamModule,
            sponsorsModule
        )
}