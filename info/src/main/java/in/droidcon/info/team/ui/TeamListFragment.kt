package `in`.droidcon.info.team.ui


import `in`.droidcon.base.adapter.GridListAdapter
import `in`.droidcon.base.event.EventObserver
import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.state.ResultState
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import `in`.droidcon.info.R
import `in`.droidcon.info.team.presentation.TeamListViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import kotlinx.android.synthetic.main.fragment_team.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamListFragment : Fragment(), GridListAdapter.ListItemClickListener {

    private val teamViewModel: TeamListViewModel by viewModel()
    private val gridListAdapter: GridListAdapter by inject { parametersOf(this) }

    private lateinit var skeleton: RecyclerViewSkeletonScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_team, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getSpeakerList()
    }

    private fun setupRecyclerView() {
        teamListView.apply {
            layoutManager = GridLayoutManager(requireActivity(), 3)
            adapter = gridListAdapter
        }
    }

    private fun getSpeakerList() {
        teamViewModel.getTeamListState().observe(viewLifecycleOwner,
            EventObserver { state ->
                when (state) {

                    is ResultState.Loading -> {
                        showSkeleton()
                    }

                    is ResultState.Success<List<GridItem>> -> {
                        gridListAdapter.submitList(state.result)
                        skeleton.hide()
                    }

                    is ResultState.Failed -> {
                        skeleton.hide()
                    }
                }
            })
    }

    private fun showSkeleton() {
        skeleton = Skeleton.bind(teamListView)
            .adapter(gridListAdapter)
            .load(R.layout.skeleton_grid_item)
            .shimmer(true)
            .count(6)
            .color(R.color.textSecondary)
            .show()
    }

    override fun onGridItemClicked(gridItem: GridItem) {
        fragmentManager?.let {
            TeamDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("speakerId", gridItem.gridId)
                }
                setTargetFragment(this@TeamListFragment, 1)
            }.show(it, tag)
        }
    }

}
