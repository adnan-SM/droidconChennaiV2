package `in`.droidcon.base.epoxy.model

import `in`.droidcon.base.R
import `in`.droidcon.base.R2
import `in`.droidcon.base.epoxy.BaseEpoxyHolder
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

/**
 * Created by Hari on 2019-10-16.
 * subtitle header model
 */
@EpoxyModelClass(layout = R2.layout.view_subtitle)
abstract class SubHeaderModel : EpoxyModelWithHolder<SubHeaderModel.Holder>() {

    @EpoxyAttribute
    lateinit var title: String

    @EpoxyAttribute
    lateinit var desc: String

    override fun bind(holder: Holder) {
        holder.headerView.text = title
        if (desc.isNotBlank()) {
            holder.descView.text = desc
        } else {
            holder.descView.visibility = View.GONE
        }
    }

    inner class Holder : BaseEpoxyHolder() {
        val headerView: TextView by bind(R.id.headerView)
        val descView: TextView by bind(R.id.descView)
    }
}