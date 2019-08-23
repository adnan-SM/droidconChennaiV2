package `in`.droidcon.info.team.ui


import `in`.droidcon.base.common.GlideApp
import `in`.droidcon.base.core.RoundedBottomSheetDialogFragment
import `in`.droidcon.base.epoxy.controller.GridDetailController
import `in`.droidcon.base.event.EventObserver
import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.state.ResultState
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import `in`.droidcon.info.R
import `in`.droidcon.info.team.presentation.TeamDetailViewModel
import android.content.Intent
import android.graphics.Outline
import android.net.Uri
import android.os.Build
import android.view.ViewOutlineProvider
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import kotlinx.android.synthetic.main.fragment_team_detail.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamDetailFragment : RoundedBottomSheetDialogFragment(), GridDetailController.GridDetailCallbacks {

    private val teamDetailViewModel: TeamDetailViewModel by viewModel()
    private val teamController: GridDetailController by inject { parametersOf(this) }
    private var teamId: String? = null
    lateinit var skeleton: RecyclerViewSkeletonScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_team_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTeamId()
        setupRecyclerView()
        getTeamInfo()
        observeTeam()
    }

    private fun setupRecyclerView() {
        teamInfoList.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = teamController.adapter
        }
    }

    private fun observeTeam() {
        teamDetailViewModel.getTeamMemberState().observe(viewLifecycleOwner, EventObserver { state ->
            when (state) {
                is ResultState.Loading -> { showSkeleton() }
                is ResultState.Success<GridItem> -> {
                    val result = state.result
                    setupImage(result.gridImg)
                    teamController.setData(result)
                    skeleton.hide()
                }
                is ResultState.Failed<String> -> {
                    skeleton.hide()
                }
            }
        })
    }

    private fun setupImage(path: String) {
        setupRoundedImage()
        GlideApp.with(teamImage.context)
            .load(path)
            .placeholder(R.color.skeleton)
            .error(R.color.skeleton)
            .into(teamImage)
    }

    private fun setupRoundedImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            teamImage.outlineProvider = object : ViewOutlineProvider() {
                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun getOutline(view: View?, outline: Outline?) {
                    outline?.setRoundRect(0, 0, view!!.width, (view.height + 25F).toInt(), 25F)
                }
            }
            teamImage.clipToOutline = true
        }
    }

    private fun getTeamInfo() {
        teamId?.let { teamDetailViewModel.getOneTeamMember(it) }
    }

    private fun getTeamId() {
        arguments?.let {
            teamId = it.getString("speakerId")
        }
    }

    private fun showSkeleton() {
        skeleton = Skeleton.bind(teamInfoList)
            .adapter(teamController.adapter)
            .load(R.layout.skeletion_info)
            .shimmer(false)
            .count(1)
            .color(R.color.textSecondary)
            .show()
    }

    override fun onTwitterButtonClicked(twitterHandle: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/$twitterHandle")))
    }

    override fun onWebsiteButtonClicked(websiteAddress: String) {
        if (websiteAddress.isNotEmpty()) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteAddress))
            startActivity(browserIntent)
        } else {
            Toast.makeText(requireContext(), "Unable to find URL", Toast.LENGTH_LONG).show()
        }
    }
}
