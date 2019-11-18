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
 * Grid info model
 */
@EpoxyModelClass(layout = R2.layout.layout_grid_info)
abstract class GridInfoModel: EpoxyModelWithHolder<GridInfoModel.Holder>() {

    @EpoxyAttribute
    lateinit var title: String

    @EpoxyAttribute
    lateinit var oneLine: String

    @EpoxyAttribute
    var blurb: String? = null

    override fun bind(holder: Holder) {
        holder.apply {
            titleView.text = title
            oneLineView.text = oneLine
            blurb?.let { blurbView.text = it }
        }
    }

    inner class Holder : BaseEpoxyHolder() {
        val titleView: TextView by bind(R.id.titleView)
        val oneLineView: TextView by bind(R.id.oneLineView)
        val blurbView: TextView by bind(R.id.blurbView)
    }
}