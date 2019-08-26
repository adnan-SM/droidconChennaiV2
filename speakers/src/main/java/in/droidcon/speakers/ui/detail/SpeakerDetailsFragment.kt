package `in`.droidcon.speakers.ui.detail


import `in`.droidcon.base.common.GlideApp
import `in`.droidcon.base.core.RoundedBottomSheetDialogFragment
import `in`.droidcon.base.epoxy.controller.GridDetailController
import `in`.droidcon.base.event.EventObserver
import `in`.droidcon.base.model.GridItem
import android.graphics.Outline
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import android.content.Intent
import android.net.Uri
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import `in`.droidcon.speakers.R
import `in`.droidcon.speakers.presentation.SpeakerDetailViewModel
import `in`.droidcon.base.state.ResultState
import android.app.Dialog
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_speaker_details.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SpeakerDetailsFragment : RoundedBottomSheetDialogFragment(),
    GridDetailController.GridDetailCallbacks {

    private val speakerDetailViewModel: SpeakerDetailViewModel by viewModel()
    private val speakerController: GridDetailController by inject { parametersOf(this) }
    private var speakerId: String? = null
    lateinit var skeleton: RecyclerViewSkeletonScreen

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_speaker_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSpeakerId()
        setupRecyclerView()
        getSpeakerInfo()
        observeSpeaker()
    }

    private fun setupRecyclerView() {
        speakerInfoList.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = speakerController.adapter
        }
    }

    private fun observeSpeaker() {
        speakerDetailViewModel.getSpeakerState()
            .observe(viewLifecycleOwner, EventObserver { state ->
                when (state) {
                    is ResultState.Loading -> {
                        showSkeleton()
                    }
                    is ResultState.Success<GridItem> -> {
                        val result = state.result
                        setupImage(result.gridImg)
                        speakerController.setData(result)
                        skeleton.hide()
                    }
                    is ResultState.Failed -> {
                        skeleton.hide()
                    }
                }
            })
    }

    private fun setupImage(path: String) {
        setupRoundedImage()
        GlideApp.with(speakerImage.context)
            .load(path)
            .placeholder(R.color.skeleton)
            .error(R.color.skeleton)
            .into(speakerImage)
    }

    private fun setupRoundedImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            speakerImage.outlineProvider = object : ViewOutlineProvider() {
                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun getOutline(view: View?, outline: Outline?) {
                    outline?.setRoundRect(0, 0, view!!.width, (view.height + 25F).toInt(), 25F)
                }
            }
            speakerImage.clipToOutline = true
        }
    }

    private fun getSpeakerInfo() {
        speakerId?.let { speakerDetailViewModel.getOneSpeaker(it) }
    }

    private fun getSpeakerId() {
        arguments?.let {
            speakerId = it.getString("speakerId")
        }
    }

    private fun showSkeleton() {
        skeleton = Skeleton.bind(speakerInfoList)
            .adapter(speakerController.adapter)
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

