package `in`.droidcon.speakers.domain

import `in`.droidcon.base.repo.remote.ServiceFactory
import `in`.droidcon.speakers.BuildConfig
import `in`.droidcon.speakers.model.SpeakerEntity
import `in`.droidcon.speakers.remote.SpeakerMapper
import `in`.droidcon.speakers.remote.SpeakerService
import io.reactivex.Single

/**
 * @author Adnan A M
 * @since  20/03/19
 */
class SpeakerRepositoryImplementation : SpeakerRepository {

    //TODO: DI with Koin
    private val speakerService: SpeakerService by lazy {
        ServiceFactory.makeService<SpeakerService>(BuildConfig.DEBUG)
    }

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