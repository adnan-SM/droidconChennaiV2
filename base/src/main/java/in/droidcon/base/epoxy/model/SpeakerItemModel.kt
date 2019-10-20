package `in`.droidcon.base.epoxy.model

import `in`.droidcon.base.R
import `in`.droidcon.base.R2
import `in`.droidcon.base.epoxy.BaseEpoxyHolder
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide

/**
 * Created by Hari on 2019-10-16.
 * speaker item model
 */
@EpoxyModelClass(layout = R2.layout.layout_speaker_item)
abstract class SpeakerItemModel : EpoxyModelWithHolder<SpeakerItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var speakerName: String

    @EpoxyAttribute
    lateinit var speakerCompany: String

    @EpoxyAttribute
    lateinit var speakerUrl: String

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null

    override fun bind(holder: Holder) {
        holder.speakerName.text = speakerName
        holder.company.text = speakerCompany
        Glide.with(holder.speakerView.context)
            .load(speakerUrl)
            .placeholder(R.drawable.ic_placeholder)
            .circleCrop()
            .error(R.drawable.ic_placeholder)
            .into(holder.speakerView)

    }

    override fun unbind(holder: Holder) {
        super.unbind(holder)
    }

    inner class Holder : BaseEpoxyHolder() {
        val speakerView: ImageView by bind(R.id.speakerView)
        val speakerName: TextView by bind(R.id.speakerName)
        val company: TextView by bind(R.id.company)
    }
}

