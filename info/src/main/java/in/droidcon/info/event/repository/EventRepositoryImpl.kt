package `in`.droidcon.info.event.repository

import `in`.droidcon.base.extension.getEventDetails
import `in`.droidcon.info.common.model.EventEntity
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

/**
 * Created by Hari on 2019-08-14.
 * Repository implementation for event details
 */
class EventRepositoryImpl : EventRepository {

    private val fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun getRepositoryDetails(): Single<List<EventEntity>> {
        return Single.create { emitter ->
            fireStore.getEventDetails()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            val list: List<EventEntity> = result.toObjects(EventEntity::class.java)
                            emitter.onSuccess(list)
                        } ?: emitter.onError(Throwable("Event details not found"))
                    } else {
                        emitter.onError(Throwable(task.exception))
                    }
                }
        }
    }
}