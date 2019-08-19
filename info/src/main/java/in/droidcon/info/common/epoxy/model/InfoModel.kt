package `in`.droidcon.info.common.epoxy.model

import `in`.droidcon.base.epoxy.BaseEpoxyHolder
import `in`.droidcon.info.R
import `in`.droidcon.info.R2
import `in`.droidcon.info.common.model.EventEntity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import org.w3c.dom.Text

/**
 * Created by Hari on 2019-08-14.
 * Epoxy model for event info
 */
@EpoxyModelClass(layout = R2.layout.layout_info_model)
abstract class InfoModel: EpoxyModelWithHolder<InfoModel.Holder>() {

    @EpoxyAttribute
    lateinit var title: String

    @EpoxyAttribute
    var desc: String? = null

    @EpoxyAttribute
    var redirectVisibility: Int = View.VISIBLE

    @EpoxyAttribute
    var buttonVisibility: Int = View.GONE

    @EpoxyAttribute
    var buttonText: String? = null

    @EpoxyAttribute
    var redirectText: String? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var actionButtonListener: View.OnClickListener? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var redirectButtonListener: View.OnClickListener? = null

    override fun bind(holder: Holder) {
        holder.apply {
            titleView.text = title.toUpperCase()
            descView.text = desc
            actionButton.visibility = buttonVisibility
            redirectView.visibility = redirectVisibility
            redirectText?.let { redirectView.text = it.toUpperCase() }
            buttonText?.let { actionButton.text = it }
            actionButton.setOnClickListener(actionButtonListener)
            redirectView.setOnClickListener(redirectButtonListener)
        }
    }

    override fun unbind(holder: Holder) {
        holder.actionButton.setOnClickListener(null)
        holder.redirectView.setOnClickListener(null)
    }

    inner class Holder : BaseEpoxyHolder() {
        val titleView: TextView by bind(R.id.titleView)
        val descView: TextView by bind(R.id.descView)
        val redirectView: TextView by bind(R.id.redirectView)
        val actionButton: Button by bind(R.id.actionButton)
    }
}