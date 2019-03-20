package `in`.droidcon.speakers.domain

import `in`.droidcon.base.core.getOneSpeaker
import `in`.droidcon.base.core.getSpeakerList
import `in`.droidcon.data.speakers.model.SpeakerEntity
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

/**
 * @author Adnan A M
 * @since  20/03/19
 */
class SpeakerRepositoryImplementation: SpeakerRepository {

    private val fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun getSpeakers(): Single<List<SpeakerEntity>> {
        return Single.create<List<SpeakerEntity>> { emitter ->
            fireStore.getSpeakerList()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            val list: List<SpeakerEntity> = result.toObjects(SpeakerEntity::class.java)
                            emitter.onSuccess(list)
                        } ?: emitter.onSuccess(listOf())
                    } else {
                        emitter.onError(Throwable(task.exception))
                    }
                }
        }
    }

    override fun getOneSpeaker(speakerId: String): Single<SpeakerEntity> {
        return Single.create<SpeakerEntity> { emitter ->
            fireStore.getOneSpeaker(speakerId)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            val speaker = result.toObject(SpeakerEntity::class.java)
                            speaker?.let { emitter.onSuccess(it) }
                                ?: emitter.onError(Throwable("Speaker not found"))
                        }
                    } else {
                        emitter.onError(Throwable(task.exception))
                    }
                }
        }
    }

}