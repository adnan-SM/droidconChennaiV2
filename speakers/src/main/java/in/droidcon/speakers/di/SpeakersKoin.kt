package `in`.droidcon.speakers.di

import `in`.droidcon.database.di.dbModule
import org.koin.standalone.StandAloneContext.loadKoinModules

/**
 * @author Adnan A M
 * @since  19/03/19
 */
object SpeakersKoin {

    fun init() = loadKoinModules(speakersModule, viewModelModule, dbModule)

}