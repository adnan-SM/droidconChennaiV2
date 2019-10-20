package `in`.droidcon.schedule.ui


import `in`.droidcon.base.core.BaseFragment
import `in`.droidcon.base.event.EventObserver
import `in`.droidcon.base.state.ResultState
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.droidcon.schedule.R
import `in`.droidcon.schedule.epoxy.ScheduleController
import `in`.droidcon.schedule.model.ScheduleEntity
import `in`.droidcon.schedule.model.TalkEntity
import `in`.droidcon.schedule.navigation.ScheduleNavigation
import `in`.droidcon.schedule.presentation.ScheduleViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import kotlinx.android.synthetic.main.fragment_day_two.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class DayTwoFragment : BaseFragment(), ScheduleController.ScheduleCallbacks {

    private val viewModel: ScheduleViewModel by viewModel()
    private val navigator: ScheduleNavigation by inject()
    private val scheduleController: ScheduleController by inject() { parametersOf(this) }

    private lateinit var skeleton: RecyclerViewSkeletonScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_day_two, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getScheduleList()
        setupRecyclerView()
    }

    override fun onScheduleClicked(entity: TalkEntity) {
        navigator.onScheduleTapped(entity)
    }

    private fun setupRecyclerView() {
        scheduleList.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = scheduleController.adapter
        }
    }

    private fun getScheduleList() {
        viewModel.getScheduleListState().observe(viewLifecycleOwner,
            EventObserver { state ->
                when (state) {

                    is ResultState.Loading -> {
                        showSkeleton()
                    }

                    is ResultState.Success<List<ScheduleEntity>> -> {
                        scheduleController.setData(state.result)
                        skeleton.hide()
                    }

                    is ResultState.Failed -> {
                        skeleton.hide()
                    }
                }
            })
    }

    private fun showSkeleton() {
        skeleton = Skeleton.bind(scheduleList)
            .adapter(scheduleController.adapter)
            .load(R.layout.skeleton_schedule)
            .shimmer(true)
            .count(4)
            .color(R.color.textSecondary)
            .show()
    }
}
