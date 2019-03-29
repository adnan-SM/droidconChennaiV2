package `in`.droidcon.base.di

import org.koin.standalone.StandAloneContext

/**
 * @author Adnan A M
 * @since  26/03/19
 */
object BaseKoin {

    fun init() = StandAloneContext.loadKoinModules(baseModule)

}