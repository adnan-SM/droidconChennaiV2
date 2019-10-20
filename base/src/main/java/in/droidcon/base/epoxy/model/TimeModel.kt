package `in`.droidcon.base.epoxy.model

import `in`.droidcon.base.R
import `in`.droidcon.base.R2
import `in`.droidcon.base.epoxy.BaseEpoxyHolder
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

/**
 * Created by Hari on 2019-10-12.
 * Epoxy time model
 */
@EpoxyModelClass(layout = R2.layout.layout_time_header)
abstract class TimeModel : EpoxyModelWithHolder<TimeModel.Holder>() {

    @EpoxyAttribute
    lateinit var time: String

    override fun bind(holder: Holder) {
        holder.timeView.text = time
    }

    inner class Holder : BaseEpoxyHolder() {
        val timeView: TextView by bind(R.id.timeView)
    }
}