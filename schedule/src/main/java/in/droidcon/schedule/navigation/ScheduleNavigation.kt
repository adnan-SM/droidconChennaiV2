package `in`.droidcon.schedule.navigation

import `in`.droidcon.schedule.model.TalkEntity

/**
 * Created by Hari on 2019-10-14.
 * Schedule navigation
 */
interface ScheduleNavigation {

    fun onScheduleTapped(talk: TalkEntity)
}