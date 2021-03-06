package `in`.droidcon.info.sponsors.ui


import `in`.droidcon.base.adapter.GridListAdapter
import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.state.ResultState
import `in`.droidcon.base.view.GridDetailBottomSheet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import `in`.droidcon.info.R
import `in`.droidcon.info.common.presentation.InfoViewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import kotlinx.android.synthetic.main.fragment_sponsors.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 *
 */
class SponsorsFragment : Fragment(), GridListAdapter.ListItemClickListener {

    private val sponsorViewModel: InfoViewModel by inject()
    private val gridListAdapter: GridListAdapter by inject { parametersOf(this) }

    private lateinit var skeleton: RecyclerViewSkeletonScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_sponsors, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitle.text = getString(R.string.sponsors)
        setupRecyclerView()
        getSpeakerList()
    }

    private fun setupRecyclerView() {
        sponsorListView.apply {
            layoutManager = GridLayoutManager(requireActivity(), 3)
            adapter = gridListAdapter
        }
    }

    private fun getSpeakerList() {
        sponsorViewModel.getSponsorListState().observe(viewLifecycleOwner,
            Observer { state ->
                when (state) {

                    is ResultState.Loading -> {
                        showSkeleton()
                    }

                    is ResultState.Success<List<GridItem>> -> {
                        errorView.visibility = View.GONE
                        showSkeleton()
                        gridListAdapter.submitList(state.result)
                        skeleton.hide()
                    }

                    is ResultState.Failed -> {
                        skeleton.hide()
                        errorView.apply {
                            text = getString(R.string.error)
                            visibility = View.VISIBLE
                        }
                    }
                }
            })
    }

    private fun showSkeleton() {
        skeleton = Skeleton.bind(sponsorListView)
            .adapter(gridListAdapter)
            .load(R.layout.skeleton_grid_item)
            .shimmer(true)
            .count(6)
            .color(R.color.textSecondary)
            .show()
    }

    override fun onGridItemClicked(gridItem: GridItem) {
        fragmentManager?.let {
            GridDetailBottomSheet().apply {
                arguments = Bundle().apply {
                    putParcelable("gridItem", gridItem)
                }
                setTargetFragment(this@SponsorsFragment, 1)
            }.show(it, tag)
        }
    }
}
