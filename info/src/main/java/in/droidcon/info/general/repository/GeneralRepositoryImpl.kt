package `in`.droidcon.info.general.repository

import `in`.droidcon.base.extension.getGeneralDetails
import `in`.droidcon.info.common.model.InfoEntity
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

/**
 * Created by Hari on 2019-08-24.
 * Repository implementation
 */
class GeneralRepositoryImpl : GeneralRepository {

    private val fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun getGeneralDetails(): Single<List<InfoEntity>> {
        return Single.create { emitter ->
            fireStore.getGeneralDetails()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            val list: List<InfoEntity> = result.toObjects(InfoEntity::class.java)
                            emitter.onSuccess(list)
                        } ?: emitter.onError(Throwable("General details not found"))
                    } else {
                        emitter.onError(Throwable(task.exception))
                    }
                }
        }
    }
}