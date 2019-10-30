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
import `in`.droidcon.speakers.ui.detail.SpeakerDetailsFragment
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
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
import org.koin.core.parameter.parametersOf
import timber.log.Timber
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


/**
 * Created by Hari on 2019-10-13.
 * Schedule detail fragment
 */
class ScheduleDetailFragment : BaseFragment(), ScheduleDetailController.ScheduleCallbacks {

    private val viewModel: ScheduleViewModel by viewModel()
    private var talkEntity: TalkEntity? = null
    private val controller: ScheduleDetailController by inject { parametersOf(this) }

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

    override fun onSpeakerClicked(speakerId: String) {
        fragmentManager?.let {
            SpeakerDetailsFragment().apply {
                arguments = Bundle().apply { putString("speakerId", speakerId) }
                setTargetFragment(this@ScheduleDetailFragment, 1)
            }.show(it, tag)
        }
    }

    override fun onTwitterButtonClicked(twitterHandle: String) {
        shareTwitter(requireActivity(), "$twitterHandle @droidconIn #droidconIN")
    }

    /**
     * Share on Twitter. Using Twitter app if installed or web link otherwise.
     *
     * @param activity activity which launches the intent
     * @param text     text to share
     * @param hashtags hashtags for tweet without '#' and separated by ','
     */
    private fun shareTwitter(activity: Activity, text: String) {
        val tweetUrl = StringBuilder("https://twitter.com/intent/tweet?text=")
        tweetUrl.append(if (TextUtils.isEmpty(text)) urlEncode(" ") else urlEncode(text))
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl.toString()))
        val matches = activity.packageManager.queryIntentActivities(intent, 0)
        for (info in matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                intent.setPackage(info.activityInfo.packageName)
            }
        }
        activity.startActivity(intent)
    }

    /**
     * Convert to UTF-8 text to put it on url format
     *
     * @param s text to be converted
     * @return text on UTF-8 format
     */
    private fun urlEncode(s: String): String {
        try {
            return URLEncoder.encode(s, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("URLEncoder.encode() failed for $s")
        }

    }

}