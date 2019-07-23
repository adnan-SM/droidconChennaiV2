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
@EpoxyModelClass(layout = R2.layout.layout_speaker_info)
abstract class SpeakerInfoModel: EpoxyModelWithHolder<SpeakerInfoModel.Holder>() {

    @EpoxyAttribute
    lateinit var speakerName: String

    @EpoxyAttribute
    lateinit var speakerOneLine: String

    @EpoxyAttribute
    lateinit var speakerBlurb: String

    override fun bind(holder: Holder) {
        holder.apply {
            speakerNameView.text = speakerName
            speakerBlurbsView.text = speakerBlurb
            speakerOneLineView.text = speakerOneLine
        }
    }

    inner class Holder : BaseEpoxyHolder() {
        val speakerNameView: TextView by bind(R.id.speakerNameView)
        val speakerOneLineView: TextView by bind(R.id.speakerOneLineView)
        val speakerBlurbsView: TextView by bind(R.id.speakerBlurbs)
    }
}