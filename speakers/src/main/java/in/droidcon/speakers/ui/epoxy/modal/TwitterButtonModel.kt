package `in`.droidcon.speakers.ui.epoxy.modal

import `in`.droidcon.base.epoxy.BaseEpoxyHolder
import `in`.droidcon.speakers.R
import `in`.droidcon.speakers.R2
import android.view.View
import android.widget.Button
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
/**
 * Created by Backbase R&D B.V on 2019-07-23.
 * Epoxy model for twitter button
 */
@EpoxyModelClass(layout = R2.layout.layout_twitter_button)
abstract class TwitterButtonModel: EpoxyModelWithHolder<TwitterButtonModel.Holder>() {

    @EpoxyAttribute
    lateinit var buttonText: String

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null

    override fun bind(holder: Holder) {
        holder.twitterButton.apply {
            text = buttonText
            setOnClickListener(clickListener)
        }
    }

    override fun unbind(holder: Holder) {
        holder.twitterButton.setOnClickListener(null)
    }

    inner class Holder : BaseEpoxyHolder() {
        val twitterButton: Button by bind(R.id.twitterButton)
    }
}