package `in`.droidcon.base.epoxy.model

import `in`.droidcon.base.R
import `in`.droidcon.base.R2
import `in`.droidcon.base.epoxy.BaseEpoxyHolder
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.chip.Chip

/**
 * Created by Hari on 2019-10-13.
 * Epoxy model for talk detail abstract
 */
@EpoxyModelClass(layout = R2.layout.layout_abstract)
abstract class TalkAbstractModel : EpoxyModelWithHolder<TalkAbstractModel.Holder>(){

    @EpoxyAttribute
    lateinit var talkTitle: String

    @EpoxyAttribute
    lateinit var time: String

    @EpoxyAttribute
    lateinit var talkDetails: String

    @EpoxyAttribute
    lateinit var abstractDetails: String

    override fun bind(holder: Holder) {
        holder.apply {
            talkTitleView.text = talkTitle
            timeView.text = time
            talkDetailsView.text = talkDetails
            abstractView.text = abstractDetails
        }

    }

    inner class Holder : BaseEpoxyHolder() {
        val talkTitleView: TextView by bind(R.id.talkTitle)
        val timeView: TextView by bind(R.id.timeView)
        val talkDetailsView: TextView by bind(R.id.trackDetailView)
        val abstractView: TextView by bind(R.id.abstractView)
    }

}