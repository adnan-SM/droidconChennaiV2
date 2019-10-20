package `in`.droidcon.chennai.navigation

import `in`.droidcon.base.navigation.MainNavigation
import `in`.droidcon.chennai.R
import `in`.droidcon.schedule.model.TalkEntity
import `in`.droidcon.schedule.navigation.ScheduleNavigation
import android.os.Bundle
import androidx.navigation.NavController

/**
 * Created by Hari on 2019-10-14.
 * Schedule Navigator
 */
class ScheduleNavigator(private val navController: MainNavigation) : ScheduleNavigation {

    override fun onScheduleTapped(talk: TalkEntity) {
        val bundle = Bundle().apply { putParcelable("talk", talk) }
        navController.getNavController()
            ?.navigate(R.id.action_homeFragment_to_scheduleDetailFragment, bundle)
    }
}