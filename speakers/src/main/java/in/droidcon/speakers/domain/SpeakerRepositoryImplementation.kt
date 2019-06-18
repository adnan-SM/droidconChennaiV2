package `in`.droidcon.speakers.domain

import `in`.droidcon.speakers.model.SpeakerEntity
import `in`.droidcon.speakers.remote.SpeakerMapper
import `in`.droidcon.speakers.remote.SpeakerService
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * @author Adnan A M
 * @since  20/03/19
 */
class SpeakerRepositoryImplementation : SpeakerRepository, KoinComponent {

    private val speakerService: SpeakerService by inject()

    val speakersCache: List<SpeakerEntity>? = null

    override fun getSpeakers(): Single<List<SpeakerEntity>> {
        return if (speakersCache == null) {
            speakerService.getSpeakers().map {
                it.map {
                    SpeakerMapper.mapFromRemote(it)
                }
            }
        } else {
            Single.defer {
                Single.just(speakersCache)
            }
        }
    }

    override fun getOneSpeaker(speakerId: String): Single<SpeakerEntity> {
        //TODO: Better Implementation
        return getSpeakers().map {
            it.find {
                it.speakerId == speakerId
            }
        }
    }

}