package `in`.droidcon.chennai.di

import `in`.droidcon.database.di.dbModule
import org.koin.standalone.StandAloneContext.loadKoinModules

/**
 * @author Adnan A M
 * @since  26/03/19
 */
object NavigatorKoin {

    fun init() = loadKoinModules(navModule)

}