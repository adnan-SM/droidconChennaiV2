package `in`.droidcon.info

import `in`.droidcon.info.event.ui.EventFragment
import `in`.droidcon.info.general.ui.GeneralFragment
import `in`.droidcon.info.sponsors.ui.SponsorsFragment
import `in`.droidcon.info.team.ui.TeamListFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.lang.IllegalStateException

/**
 * Created by Hari on 2019-08-09.
 * View pager for fragments
 */
class InfoPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> EventFragment()
            1 -> TeamListFragment()
            2 -> GeneralFragment()
            else -> throw IllegalStateException("no fragment is assigned for position - $position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Event"
            1 -> "Team"
            2 -> "General"
            else -> "Droidcon India"
        }
    }

    override fun getCount(): Int = 3
}