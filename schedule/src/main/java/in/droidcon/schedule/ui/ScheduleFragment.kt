package `in`.droidcon.schedule.ui

import `in`.droidcon.base.core.BaseFragment
import `in`.droidcon.schedule.R
import `in`.droidcon.schedule.presentation.ScehduleAdapter
import `in`.droidcon.schedule.presentation.ScheduleViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_schedule.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Hari on 2019-10-12.
 * Parent fragment of scheule
 */
class ScheduleFragment : BaseFragment() {

    private val viewModel: ScheduleViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_schedule, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitle.text = getString(R.string.schedule)
        val viewPager: ViewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = ScehduleAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}