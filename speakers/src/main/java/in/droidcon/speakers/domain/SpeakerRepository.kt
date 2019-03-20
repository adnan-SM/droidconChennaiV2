package `in`.droidcon.speakers.domain

import `in`.droidcon.data.speakers.model.SpeakerEntity
import `in`.droidcon.speakers.model.SpeakerItem
import io.reactivex.Single

interface SpeakerRepository {

    fun getSpeakers(): Single<List<SpeakerEntity>>

    fun getOneSpeaker(id: String): Single<SpeakerEntity>
}