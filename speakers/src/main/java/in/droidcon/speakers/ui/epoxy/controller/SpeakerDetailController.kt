package `in`.droidcon.speakers.ui.epoxy.controller

import `in`.droidcon.speakers.model.SpeakerItem
import `in`.droidcon.speakers.ui.epoxy.modal.speakerInfo
import `in`.droidcon.speakers.ui.epoxy.modal.twitterButton
import com.airbnb.epoxy.TypedEpoxyController

/**
 * Created by Hari on 2019-07-23.
 * Controller for epoxy; speaker detail
 */
class SpeakerDetailController(private val callbacks: SpeakerDetailCallbacks): TypedEpoxyController<SpeakerItem>() {

    override fun buildModels(data: SpeakerItem) {

        speakerInfo {
            id(data.speakerId)
            speakerName(data.speakerName)
            speakerOneLine(data.speakerOneLiner)
            speakerBlurb(data.speakerBlurbs)
        }

        twitterButton {
            id(data.speakerId)
            buttonText("@${data.speakerHandle}")
            clickListener { _ -> callbacks.onTwitterButtonClicked(data.speakerHandle) }
        }
    }

    interface SpeakerDetailCallbacks {
        fun onTwitterButtonClicked(speakerHandle: String)
    }
}