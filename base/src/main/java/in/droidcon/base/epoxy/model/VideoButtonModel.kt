package `in`.droidcon.base.epoxy.model

import `in`.droidcon.base.R
import `in`.droidcon.base.R2
import `in`.droidcon.base.epoxy.BaseEpoxyHolder
import android.view.View
import android.widget.Button
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

/**
 * Created by Hari on 2019-10-16.
 * Video button model
 */
@EpoxyModelClass(layout = R2.layout.view_layout_video_button)
abstract class VideoButtonModel: EpoxyModelWithHolder<VideoButtonModel.Holder>() {

    @EpoxyAttribute
    lateinit var buttonText: String

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null

    override fun bind(holder: VideoButtonModel.Holder) {
        holder.videoButton.apply {
            text = buttonText
            setOnClickListener(clickListener)
        }
    }

    override fun unbind(holder: VideoButtonModel.Holder) {
        holder.videoButton.setOnClickListener(null)
    }

    inner class Holder : BaseEpoxyHolder() {
        val videoButton: Button by bind(R.id.videoButton)
    }
}