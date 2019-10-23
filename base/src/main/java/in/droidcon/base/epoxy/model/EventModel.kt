package `in`.droidcon.base.epoxy.model

import `in`.droidcon.base.R
import `in`.droidcon.base.R2
import `in`.droidcon.base.epoxy.BaseEpoxyHolder
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

/**
 * Created by Hari on 2019-10-23.
 * Event model for schedule
 */
@EpoxyModelClass(layout = R2.layout.layout_schedule_event)
abstract class EventModel : EpoxyModelWithHolder<EventModel.Holder>() {

    @EpoxyAttribute
    lateinit var eventName: String

    override fun bind(holder: Holder) {
        holder.eventText.text = eventName
    }

    inner class Holder : BaseEpoxyHolder() {
        val eventText: TextView by bind(R.id.eventText)
    }
}