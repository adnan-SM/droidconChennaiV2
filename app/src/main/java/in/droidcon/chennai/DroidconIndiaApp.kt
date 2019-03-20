package `in`.droidcon.chennai

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

    private fun initDIModules() {

        SpeakersKoin.init()

    }

}