package `in`.droidcon.schedule.presentation

import `in`.droidcon.schedule.ui.DayOneFragment
import `in`.droidcon.schedule.ui.DayTwoFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.lang.IllegalStateException

/**
 * Created by Hari on 2019-10-19.
 * Schedule adapter
 */
class ScehduleAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> DayOneFragment()
            1 -> DayTwoFragment()
            else -> throw IllegalStateException("no fragment is assigned for position - $position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Day 1"
            1 -> "Day 2"
            else -> "Droidcon India"
        }
    }

    override fun getCount(): Int = 2
}