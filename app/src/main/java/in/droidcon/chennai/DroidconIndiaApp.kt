package `in`.droidcon.chennai

import `in`.droidcon.base.di.BaseKoin
import `in`.droidcon.chennai.di.NavigatorKoin
import `in`.droidcon.info.event.di.EventKoin
import `in`.droidcon.info.team.di.TeamKoin
import `in`.droidcon.speakers.di.SpeakersKoin
import android.app.Application

/**
 * @author Adnan A M
 * @since  19/03/19
 */
class DroidconIndiaApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initDIModules()
    }

    // Initialise all DI modules across the app... Koin does lazy init so this won't have any issues
    private fun initDIModules() {

        //Init Nav Module
        NavigatorKoin.init()
        // Please ensure base DI modules are loaded first
        BaseKoin.init()
        // Load Speakers DI Module
        SpeakersKoin.init()
        TeamKoin.init()
        EventKoin.init()

    }

}