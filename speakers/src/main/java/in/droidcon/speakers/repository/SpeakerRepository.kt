package `in`.droidcon.speakers.repository

import `in`.droidcon.base.model.GridItem
import io.reactivex.Single

interface SpeakerRepository {

    fun getSpeakers(): Single<List<GridItem>>

    fun getOneSpeaker(speakerId: String): Single<GridItem>
}