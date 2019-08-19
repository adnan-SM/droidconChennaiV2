package `in`.droidcon.speakers.ui.detail


import `in`.droidcon.base.common.GlideApp
import `in`.droidcon.base.core.RoundedBottomSheetDialogFragment
import `in`.droidcon.base.event.EventObserver
import android.graphics.Outline
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
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
import `in`.droidcon.speakers.model.SpeakerItem
import `in`.droidcon.speakers.presentation.SpeakerDetailViewModel
import `in`.droidcon.speakers.state.TaskState
import `in`.droidcon.speakers.ui.epoxy.controller.SpeakerDetailController
import kotlinx.android.synthetic.main.fragment_speaker_details.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SpeakerDetailsFragment : RoundedBottomSheetDialogFragment(), SpeakerDetailController.SpeakerDetailCallbacks {

    private val speakerDetailViewModel: SpeakerDetailViewModel by viewModel()
    private val speakerController: SpeakerDetailController by inject { parametersOf(this) }
    private var speakerId: String? = null
    lateinit var skeleton: RecyclerViewSkeletonScreen

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
        speakerDetailViewModel.getSpeakerState().observe(viewLifecycleOwner, EventObserver { state ->
            when (state) {
                is TaskState.Loading -> { showSkeleton() }
                is TaskState.Success<*> -> {
                    val result = state.result as SpeakerItem
                    setupImage(result.speakerImg)
                    speakerController.setData(result)
                    skeleton.hide()
                }
                is TaskState.Failed -> {
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
            .load(R.layout.skeletion_speaker_info)
            .shimmer(false)
            .count(1)
            .color(R.color.textSecondary)
            .show()
    }

    override fun onTwitterButtonClicked(speakerHandle: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/$speakerHandle")))
    }


}
