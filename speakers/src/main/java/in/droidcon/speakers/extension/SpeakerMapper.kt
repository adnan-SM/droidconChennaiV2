package `in`.droidcon.speakers.extension

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.data.speakers.model.SpeakerEntity

/**
 * Created by Hari on 2019-07-23.
 * Extensions for Speaker module
 */
fun SpeakerEntity.mapToDomain(): GridItem {
    return GridItem(
        this.speakerId,
        this.speakerName,
        this.speakerOrg,
        this.speakerLocation,
        this.speakerOneLiner,
        this.speakerHandle,
        null,
        this.speakerBlurbs,
        this.speakerImg,
        this.talkId
    )
}