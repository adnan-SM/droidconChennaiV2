package `in`.droidcon.base.epoxy.controller

import `in`.droidcon.base.epoxy.model.gridInfo
import `in`.droidcon.base.epoxy.model.twitterButton
import `in`.droidcon.base.model.GridItem
import com.airbnb.epoxy.TypedEpoxyController

/**
 * Created by Hari on 2019-08-10.
 * Controller for epoxy; grid detail
 */
class GridDetailController(private val callbacks: GridDetailCallbacks): TypedEpoxyController<GridItem>() {

    override fun buildModels(data: GridItem) {

        gridInfo {
            id(data.gridId)
            title(data.gridName)
            oneLine(data.gridOrg)
            blurb(data.gridBlurbs)
        }

        twitterButton {
            id(data.gridId)
            buttonText("@${data.gridHandle}")
            clickListener { _ -> callbacks.onTwitterButtonClicked(data.gridHandle) }
        }
    }

    interface GridDetailCallbacks {
        fun onTwitterButtonClicked(twitterHandle: String)
    }
}