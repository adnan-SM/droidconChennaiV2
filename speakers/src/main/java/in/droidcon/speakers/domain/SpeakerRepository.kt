package `in`.droidcon.speakers.domain

import `in`.droidcon.speakers.model.SpeakerItem
import io.reactivex.Single

interface SpeakerRepository {

    fun getSpeakers(): Single<List<SpeakerItem>>

    fun getOneSpeaker(speakerId: String): Single<SpeakerItem>
}