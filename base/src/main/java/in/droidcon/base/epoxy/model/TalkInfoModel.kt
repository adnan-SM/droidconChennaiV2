package `in`.droidcon.base.epoxy.model

import `in`.droidcon.base.R
import `in`.droidcon.base.R2
import `in`.droidcon.base.epoxy.BaseEpoxyHolder
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

/**
 * Created by Hari on 2019-08-10.
 * Model for epoxy
 */
@EpoxyModelClass(layout = R2.layout.layout_talk_info)
abstract class TalkInfoModel : EpoxyModelWithHolder<TalkInfoModel.Holder>() {

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