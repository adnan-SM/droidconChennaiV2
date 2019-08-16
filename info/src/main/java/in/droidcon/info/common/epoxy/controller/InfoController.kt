package `in`.droidcon.info.common.epoxy.controller

import `in`.droidcon.info.common.epoxy.model.info
import `in`.droidcon.info.common.model.EventEntity
import android.view.View
import com.airbnb.epoxy.TypedEpoxyController

/**
 * Created by Hari on 2019-08-15.
 * Info epoxy controller
 */
class InfoController(private val callbacks: InfoCallbacks) : TypedEpoxyController<List<EventEntity>>() {

    override fun buildModels(data: List<EventEntity>) {

        data.filter { it.type != TYPE_LOCATION }
            .forEachIndexed { position, item ->
                info {
                    id(position)
                    title(item.title)
                    desc(getDescription(item))
                    buttonText(item.buttonText)
                    redirectText(item.redirectText)
                    redirectVisibility(getVisibility(item.redirectText))
                    buttonVisibility(getVisibility(item.buttonText))
                    actionButtonListener { _ -> getCallback(item) }
                    redirectButtonListener { _ -> getCallback(item) }
                }
            }
    }

    private fun getVisibility(value: String?): Int {
        return if (value != null) View.VISIBLE else View.GONE
    }

    private fun getDescription(item: EventEntity): String {
        val descLineOne: String = item.desc ?: ""
        val descLineTwo: String = if (item.desc2.isNullOrEmpty()) "" else "\n${item.desc2}"
        return descLineOne + descLineTwo
    }

    private fun getCallback(data: EventEntity) {
        when (data.buttonType) {
            BUTTON_TYPE_CALL -> callbacks.onCallActionClicked(data.buttonText)
            BUTTON_TYPE_COPY -> callbacks.onCopyClicked(data.copy)
            BUTTON_TYPE_LINK -> callbacks.onUrlClicked(data.redirectUrl)
            BUTTON_TYPE_LOCATION -> callbacks.onMapClicked(data.destination)
        }
    }

    interface InfoCallbacks {
        fun onCallActionClicked(number: String?)
        fun onUrlClicked(url: String?)
        fun onMapClicked(address: String?)
        fun onCopyClicked(copyItem: String?)
    }

    companion object {
        const val BUTTON_TYPE_CALL = "Call"
        const val BUTTON_TYPE_LINK = "Link"
        const val BUTTON_TYPE_LOCATION = "Location"
        const val BUTTON_TYPE_COPY = "Copy"
        const val TYPE_LOCATION = "Location"
    }
}