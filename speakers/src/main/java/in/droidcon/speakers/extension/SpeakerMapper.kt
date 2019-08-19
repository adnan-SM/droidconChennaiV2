package `in`.droidcon.speakers.extension

import `in`.droidcon.data.speakers.model.SpeakerEntity
import `in`.droidcon.speakers.model.SpeakerItem

/**
 * Created by Hari on 2019-07-23.
 * Extensions for Speaker module
 */
fun SpeakerEntity.mapToDomain(): SpeakerItem {
    return SpeakerItem(
        this.speakerId,
        this.speakerName,
        this.speakerOrg,
        this.speakerLocation,
        this.speakerOneLiner,
        this.speakerHandle,
        this.speakerBlurbs,
        this.speakerImg,
        this.talkId
    )
}