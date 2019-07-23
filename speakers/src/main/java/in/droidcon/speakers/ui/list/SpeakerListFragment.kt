package `in`.droidcon.speakers.ui.list

import `in`.droidcon.base.core.BaseFragment
import `in`.droidcon.base.event.EventObserver
import `in`.droidcon.data.speakers.model.SpeakerEntity
import `in`.droidcon.speakers.R
import `in`.droidcon.speakers.model.SpeakerItem
import `in`.droidcon.speakers.ui.adapter.SpeakerListAdapter
import `in`.droidcon.speakers.state.TaskState
import `in`.droidcon.speakers.presentation.SpeakerListViewModel
import `in`.droidcon.speakers.ui.detail.SpeakerDetailsFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import kotlinx.android.synthetic.main.fragment_speakers_list.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * @author Adnan A M
 * @since  18/03/19
 */
class SpeakerListFragment : BaseFragment(), SpeakerListAdapter.ListItemClickListener {

    private val speakersViewModel: SpeakerListViewModel by viewModel()
    private val speakersAdapter: SpeakerListAdapter by inject { parametersOf(this) }

    private lateinit var skeleton: RecyclerViewSkeletonScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_speakers_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getSpeakerList()
    }

    private fun setupRecyclerView() {
        speakerListView.apply {
            layoutManager = GridLayoutManager(requireActivity(), 3)
            adapter = speakersAdapter
        }
    }

    private fun getSpeakerList() {
        speakersViewModel.getSpeakersListState().observe(viewLifecycleOwner,
            EventObserver { state ->
                when (state) {

                    is TaskState.Loading -> {
                        showSkeleton()
                    }

                    is TaskState.Success<*> -> {
                        val list = state.result as List<*>
                        val speakerList = list.map { it as SpeakerItem }
                        speakersAdapter.submitList(speakerList)
                        skeleton.hide()
                    }

                    is TaskState.Failed -> {
                        skeleton.hide()
                    }
                }
            })
    }

    private fun showSkeleton() {
        skeleton = Skeleton.bind(speakerListView)
            .adapter(speakersAdapter)
            .load(R.layout.skeleton_speaker_item)
            .shimmer(true)
            .count(6)
            .color(R.color.textSecondary)
            .show()
    }

    override fun onSpeakerItemClicked(speakerItem: SpeakerItem) {
        fragmentManager?.let {
            SpeakerDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString("speakerId", speakerItem.speakerId)
                }
                setTargetFragment(this@SpeakerListFragment, 1)
            }.show(it, tag)
        }
    }
}