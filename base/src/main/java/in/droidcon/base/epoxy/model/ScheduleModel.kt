package `in`.droidcon.base.epoxy.model

import `in`.droidcon.base.R
import `in`.droidcon.base.R2
import `in`.droidcon.base.epoxy.BaseEpoxyHolder
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.chip.Chip

/**
 * Created by Hari on 2019-10-12.
 * epoxy model for schedule
 */
@EpoxyModelClass(layout = R2.layout.layout_schedule)
abstract class ScheduleModel : EpoxyModelWithHolder<ScheduleModel.Holder>() {

    @EpoxyAttribute
    lateinit var talkTitle: String

    @EpoxyAttribute
    lateinit var speaker: String

    @EpoxyAttribute
    lateinit var talkDetails: String

    @EpoxyAttribute
    lateinit var category: String

    @EpoxyAttribute
    var categoryBgColor: Int = 0

    @EpoxyAttribute
    var categoryTextColor: Int = 0

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null

    override fun bind(holder: Holder) {
        holder.apply {
            talkTitleView.text = talkTitle
            speakerView.text = speaker
            talkDetailsView.text = talkDetails
            categoryView.setChipBackgroundColorResource(categoryBgColor)
            categoryView.setTextColor(categoryTextColor)
            categoryView.text = category
            itemView.setOnClickListener(clickListener)
        }
    }

    override fun unbind(holder: Holder) {
        holder.itemView.setOnClickListener(null)
    }

    inner class Holder : BaseEpoxyHolder() {
        val talkTitleView: TextView by bind(R.id.talkTitle)
        val speakerView: TextView by bind(R.id.speaker)
        val talkDetailsView: TextView by bind(R.id.talkDetails)
        val categoryView: Chip by bind(R.id.category)
        val itemView: ConstraintLayout by bind(R.id.itemView)
    }
}