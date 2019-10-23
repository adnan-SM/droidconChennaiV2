package `in`.droidcon.schedule.ui

import `in`.droidcon.base.core.BaseFragment
import `in`.droidcon.base.event.EventObserver
import `in`.droidcon.base.model.SpeakerEntity
import `in`.droidcon.base.state.ResultState
import `in`.droidcon.base.util.CategoryUtil
import `in`.droidcon.schedule.R
import `in`.droidcon.schedule.epoxy.ScheduleDetailController
import `in`.droidcon.schedule.model.TalkEntity
import `in`.droidcon.schedule.presentation.ScheduleViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import kotlinx.android.synthetic.main.fragment_schedule_detail.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * Created by Hari on 2019-10-13.
 * Schedule detail fragment
 */
class ScheduleDetailFragment : BaseFragment() {

    private val viewModel: ScheduleViewModel by viewModel()
    private var talkEntity: TalkEntity? = null
    private val controller: ScheduleDetailController by inject()

    private lateinit var skeleton: RecyclerViewSkeletonScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_schedule_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        getSpeakers()
        observeSpeakers()
    }

    private fun observeSpeakers() {
        viewModel.getSpeakerListState().observe(viewLifecycleOwner,  EventObserver { state ->
            when (state) {

                is ResultState.Loading -> {
                    showSkeleton()
                }

                is ResultState.Success<List<SpeakerEntity>> -> {
                    Timber.d(state.result.toString())
                    showData(state.result)
                    skeleton.hide()
                }

                is ResultState.Failed -> {
                    skeleton.hide()
                    showData(listOf())
                }
            }
        })
    }

    private fun showData(list: List<SpeakerEntity>) {
        talkEntity?.let {
            val image = CategoryUtil.categoryMap[it.category]?.drawable
            Glide.with(requireActivity()).load(image).into(category)
            scheduleDetailList.adapter = controller.adapter
            controller.setData(it, list)
        }
    }

    private fun getSpeakers() {
        talkEntity = arguments?.getParcelable("talk")
        talkEntity?.let { viewModel.querySpeaker(it.id) }
    }

    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun showSkeleton() {
        skeleton = Skeleton.bind(scheduleDetailList)
            .adapter(controller.adapter)
            .load(R.layout.skeleton_schedule_detail)
            .shimmer(true)
            .count(1)
            .color(R.color.textSecondary)
            .show()
    }
}