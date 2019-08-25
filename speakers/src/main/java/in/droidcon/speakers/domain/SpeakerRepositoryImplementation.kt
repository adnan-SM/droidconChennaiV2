package `in`.droidcon.speakers.domain

import `in`.droidcon.database.EventDAO
import `in`.droidcon.database.entities.Speaker
import `in`.droidcon.speakers.model.SpeakerEntity
import `in`.droidcon.speakers.remote.SpeakerMapper
import `in`.droidcon.speakers.remote.SpeakerModel
import `in`.droidcon.speakers.remote.SpeakerService
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.lang.Exception

/**
 * @author Adnan A M
 * @since  20/03/19
 */
class SpeakerRepositoryImplementation(private val speakerService: SpeakerService, private val eventDAO: EventDAO) : SpeakerRepository {

    var speakersCache: List<SpeakerEntity>? = null

    override fun getSpeakers(): Single<List<SpeakerEntity>> {
        return eventDAO.getAllSpeakers()
            .map { speakerList ->
                if(speakerList.isEmpty()) throw Exception()
                else speakerList.map {
                    SpeakerMapper.mapFromRemote(
                        SpeakerModel(
                            id = it.id,
                            bio = it.bio,
                            firstName = it.firstName,
                            fullName = it.fullName,
                            lastName = it.lastName,
                            links = listOf(),
                            profilePicture = it.profilePicture,
                            tagLine = it.tagLine,
                            isTopSpeaker = it.isTopSpeaker,
                            sessions = listOf()
                        )
                    )
                }
            }.onErrorResumeNext(
                if (speakersCache == null) {
                    speakerService.getSpeakers()
                        .flatMap { speakerList ->
                            eventDAO.insertSpeakers(
                                speakerList.map {
                                    Speaker(
                                        id = it.id,
                                        bio = it.bio,
                                        categoryItems = listOf(),
                                        firstName = it.firstName,
                                        fullName = it.fullName,
                                        lastName = it.lastName,
                                        links = listOf(),
                                        profilePicture = it.profilePicture,
                                        tagLine = it.tagLine,
                                        isTopSpeaker = it.isTopSpeaker,
                                        questionAnswers = listOf(),
                                        sessions = it.sessions.map { session ->
                                            "${session.id}"
                                        }
                                    )
                                }
                            )
                                .andThen(Single.just(speakerList))
                        }
                        .map {
                            it.map(SpeakerMapper::mapFromRemote).also { speakerList ->
                                speakersCache = speakerList
                            }
                        }
                } else {
                    Single.defer {
                        Single.just(speakersCache)
                    }
                }
            )
    }

    override fun getOneSpeaker(speakerId: String): Single<SpeakerEntity> {
        //TODO: Better Implementation
        return getSpeakers().map { speakerList ->
            speakerList.find { speaker ->
                speaker.speakerId == speakerId
            }
        }
    }

}