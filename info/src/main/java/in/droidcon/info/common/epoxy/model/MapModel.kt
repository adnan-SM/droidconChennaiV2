package `in`.droidcon.info.common.epoxy.model

import `in`.droidcon.base.epoxy.BaseEpoxyHolder
import `in`.droidcon.info.R
import `in`.droidcon.info.R2
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

/**
 * Created by Backbase R&D B.V on 2019-08-16.
 * Epoxy model for map
 */
@EpoxyModelClass(layout = R2.layout.layout_map_model)
abstract class MapModel: EpoxyModelWithHolder<MapModel.Holder>() {

    @EpoxyAttribute
    lateinit var title: String

    @EpoxyAttribute
    var redirectText: String? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var redirectButtonListener: View.OnClickListener? = null

    override fun bind(holder: Holder) {
        holder.apply {
            titleView.text = title.toUpperCase()
            redirectText?.let { redirectView.text = it.toUpperCase() }
            redirectView.setOnClickListener(redirectButtonListener)
        }
    }

    override fun unbind(holder: Holder) {
        holder.redirectView.setOnClickListener(null)
    }

    inner class Holder : BaseEpoxyHolder() {
        val titleView: TextView by bind(R.id.titleView)
        val redirectView: TextView by bind(R.id.redirectView)
    }

}