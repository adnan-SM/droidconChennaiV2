package `in`.droidcon.schedule.epoxy

import `in`.droidcon.base.epoxy.model.*
import `in`.droidcon.base.model.SpeakerEntity
import `in`.droidcon.schedule.model.TalkEntity
import com.airbnb.epoxy.Typed2EpoxyController

/**
 * Created by Hari on 2019-10-13.
 * Schedule detail controller
 */
class ScheduleDetailController(private val callbacks: ScheduleCallbacks) : Typed2EpoxyController<TalkEntity, List<SpeakerEntity>>() {

    private var speakerHandle: String = ""

    override fun buildModels(talk: TalkEntity, speaker: List<SpeakerEntity>) {

        talkAbstract {
            id(talk.hashCode())
            talkTitle(talk.title)
            time("${talk.date}, ${talk.time}")
            talkDetails("${talk.duration} | ${talk.track}")
            abstractDetails(talk.abstract)
        }

        if (!talk.videoUrl.isNullOrEmpty()) {
            videoButton {
                id(talk.videoUrl.toString())
                buttonText("Play Video")
            }
        }

        if (speaker.isNotEmpty()) {
            subHeader {
                id("speaker")
                title("Speakers")
                desc("")
            }

            speaker.forEach {
                speakerItem {
                    id(it.speakerId)
                    speakerName(it.speakerName)
                    speakerCompany(it.speakerOrg)
                    speakerUrl(it.speakerImg)
                    clickListener { _ -> callbacks.onSpeakerClicked(it.speakerId) }
                }

                speakerHandle+="@"+it.speakerHandle+" "
            }
        }

        subHeader {
            id("tweet")
            title("Tweet about it!")
            desc("Are you excited? was the talk good? tweet your support to our fab speaker(s)")
        }

        twitterButton {
            id("tweet+now")
            buttonText("Tweet Now")
            clickListener { _ -> callbacks.onTwitterButtonClicked(speakerHandle) }
        }
    }

    interface ScheduleCallbacks {
        fun onSpeakerClicked(speakerId: String)
        fun onTwitterButtonClicked(twitterHandle: String)
    }
}