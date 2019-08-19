package `in`.droidcon.speakers.ui.epoxy.modal

import `in`.droidcon.base.epoxy.BaseEpoxyHolder
import `in`.droidcon.speakers.R
import `in`.droidcon.speakers.R2
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

/**
 * Created by Hari on 2019-07-23.
 * Model for epoxy
 */
@EpoxyModelClass(layout = R2.layout.layout_speaker_info_talk)
abstract class SpeakerInfoTalkModel : EpoxyModelWithHolder<SpeakerInfoTalkModel.Holder>() {

    @EpoxyAttribute
    lateinit var talkTime: String

    @EpoxyAttribute
    lateinit var talkSummary: String

    @EpoxyAttribute
    lateinit var talkTitle: String

    override fun bind(holder: Holder) {
        holder.apply {
            talkSummaryView.text = talkSummary
            talkTimeView.text = talkTime
            talkTitleView.text = talkTitle
        }
    }


    inner class Holder : BaseEpoxyHolder() {
        val talkTimeView: TextView by bind(R.id.talkTimeView)
        val talkSummaryView: TextView by bind(R.id.talkSummaryView)
        val talkTitleView: TextView by bind(R.id.talkTitleView)
    }
}