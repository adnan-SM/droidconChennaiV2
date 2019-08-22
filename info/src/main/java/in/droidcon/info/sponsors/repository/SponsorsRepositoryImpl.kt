package `in`.droidcon.info.sponsors.repository

import `in`.droidcon.base.extension.getOneSponsor
import `in`.droidcon.base.extension.getsponsorList
import `in`.droidcon.base.model.GridItem
import `in`.droidcon.info.sponsors.extension.mapToDomain
import `in`.droidcon.info.sponsors.model.SponsorsEntity
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

/**
 * Created by Backbase R&D B.V on 2019-08-22.
 * Repository implementation - sponsors
 */
class SponsorsRepositoryImpl : SponsorsRepository {

    private val fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun getOneSponsor(id: String): Single<GridItem> {
        return Single.create<GridItem> { emitter ->
            fireStore.getOneSponsor(id)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            val teamMember =
                                result.toObject(SponsorsEntity::class.java)?.mapToDomain()
                            teamMember?.let { emitter.onSuccess(it) }
                                ?: emitter.onError(Throwable("Sponsor Member not found"))
                        }
                    } else {
                        emitter.onError(Throwable(task.exception))
                    }
                }
        }
    }

    override fun getSponsors(): Single<List<GridItem>> {
        return Single.create { emitter ->
            fireStore.getsponsorList()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            val list: List<GridItem> = result.toObjects(SponsorsEntity::class.java)
                                .map { it.mapToDomain() }
                            emitter.onSuccess(list)
                        } ?: emitter.onError(Throwable("Sponsors not found"))
                    } else {
                        emitter.onError(Throwable(task.exception))
                    }
                }
        }
    }
}