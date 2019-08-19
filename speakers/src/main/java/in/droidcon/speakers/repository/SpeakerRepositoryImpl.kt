package `in`.droidcon.speakers.repository

import `in`.droidcon.base.extension.getOneSpeaker
import `in`.droidcon.base.extension.getSpeakerList
import `in`.droidcon.data.speakers.model.SpeakerEntity
import `in`.droidcon.speakers.extension.mapToDomain
import `in`.droidcon.speakers.model.SpeakerItem
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

/**
 * @author Adnan A M
 * @since  20/03/19
 */
class SpeakerRepositoryImpl: SpeakerRepository {

    private val fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun getSpeakers(): Single<List<SpeakerItem>> {
        return Single.create<List<SpeakerItem>> { emitter ->
            fireStore.getSpeakerList()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            val list: List<SpeakerItem> = result.toObjects(SpeakerEntity::class.java)
                                .map { it.mapToDomain() }
                            emitter.onSuccess(list)
                        } ?: emitter.onError(Throwable("Speakers not found"))
                    } else {
                        emitter.onError(Throwable(task.exception))
                    }
                }
        }
    }

    override fun getOneSpeaker(speakerId: String): Single<SpeakerItem> {
        return Single.create<SpeakerItem> { emitter ->
            fireStore.getOneSpeaker(speakerId)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            val speaker = result.toObject(SpeakerEntity::class.java)?.mapToDomain()
                            speaker?.let { emitter.onSuccess(it) } ?: emitter.onError(Throwable("Speaker not found"))
                        }
                    } else {
                        emitter.onError(Throwable(task.exception))
                    }
                }
        }
    }

}