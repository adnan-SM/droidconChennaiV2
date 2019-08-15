package `in`.droidcon.info.event.di

import org.koin.standalone.StandAloneContext

/**
 * Created by Hari B.V on 2019-08-14.
 * event module service location
 */
object EventKoin {

    fun init() = StandAloneContext.loadKoinModules(eventModule)
}