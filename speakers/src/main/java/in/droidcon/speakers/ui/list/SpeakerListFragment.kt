package `in`.droidcon.speakers.ui.list

import `in`.droidcon.base.adapter.GridListAdapter
import `in`.droidcon.base.core.BaseFragment
import `in`.droidcon.base.event.EventObserver
import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.state.ResultState
import `in`.droidcon.speakers.R
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
class SpeakerListFragment : BaseFragment(), GridListAdapter.ListItemClickListener {

    private val speakersViewModel: SpeakerListViewModel by viewModel()
    private val speakersAdapter: GridListAdapter by inject { parametersOf(this) }

    private lateinit var skeleton: RecyclerViewSkeletonScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_speakers_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitle.text = getString(R.string.speakers)
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

                    is ResultState.Loading -> {
                        showSkeleton()
                    }

                    is ResultState.Success<List<GridItem>> -> {
                        errorView.visibility = View.GONE
                        speakersAdapter.submitList(state.result)
                        skeleton.hide()
                    }

                    is ResultState.Failed -> {
                        errorView.apply {
                            text = getString(R.string.error)
                            visibility = View.VISIBLE
                        }
                        skeleton.hide()
                    }
                }
            })
    }

    private fun showSkeleton() {
        skeleton = Skeleton.bind(speakerListView)
            .adapter(speakersAdapter)
            .load(R.layout.skeleton_grid_item)
            .shimmer(true)
            .count(6)
            .color(R.color.textSecondary)
            .show()
    }

    override fun onGridItemClicked(gridItem: GridItem) {
        fragmentManager?.let {
            SpeakerDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString("speakerId", gridItem.gridId)
                }
                setTargetFragment(this@SpeakerListFragment, 1)
            }.show(it, tag)
        }
    }
}