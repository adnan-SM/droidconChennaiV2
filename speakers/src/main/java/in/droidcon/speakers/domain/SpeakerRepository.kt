package `in`.droidcon.speakers.domain

import `in`.droidcon.speakers.model.SpeakerEntity
import io.reactivex.Single

interface SpeakerRepository {

    fun getSpeakers(): Single<List<SpeakerEntity>>

    fun getOneSpeaker(speakerId: String): Single<SpeakerEntity>
}