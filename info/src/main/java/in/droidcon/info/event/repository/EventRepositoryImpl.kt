package `in`.droidcon.info.event.repository

import `in`.droidcon.base.extension.getEventDetails
import `in`.droidcon.info.common.model.InfoEntity
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

/**
 * Created by Hari on 2019-08-14.
 * Repository implementation for event details
 */
class EventRepositoryImpl : EventRepository {

    private val fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun getEventDetails(): Single<List<InfoEntity>> {
        return Single.create { emitter ->
            fireStore.getEventDetails()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            val list: List<InfoEntity> = result.toObjects(InfoEntity::class.java)
                            emitter.onSuccess(list)
                        } ?: emitter.onError(Throwable("Event details not found"))
                    } else {
                        emitter.onError(Throwable(task.exception))
                    }
                }
        }
    }
}