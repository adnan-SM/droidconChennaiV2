package `in`.droidcon.info.team.repository

import `in`.droidcon.base.extension.getOneTeamMember
import `in`.droidcon.base.extension.getTeamList
import `in`.droidcon.base.model.GridItem
import `in`.droidcon.info.team.extension.mapToDomain
import `in`.droidcon.info.team.model.TeamEntity
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

/**
 * @author Hari
 * @since  10/08/19
 */
class TeamRepositoryImpl: TeamRepository {

    private val fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun getTeam(): Single<List<GridItem>> {
        return Single.create<List<GridItem>> { emitter ->
            fireStore.getTeamList()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            val list: List<GridItem> = result.toObjects(TeamEntity::class.java)
                                .map { it.mapToDomain() }
                            emitter.onSuccess(list)
                        } ?: emitter.onError(Throwable("Team not found"))
                    } else {
                        emitter.onError(Throwable(task.exception))
                    }
                }
        }
    }

    override fun getOneTeamMember(id: String): Single<GridItem> {
        return Single.create<GridItem> { emitter ->
            fireStore.getOneTeamMember(id)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            val teamMember = result.toObject(TeamEntity::class.java)?.mapToDomain()
                            teamMember?.let { emitter.onSuccess(it) } ?: emitter.onError(Throwable("Team Member not found"))
                        }
                    } else {
                        emitter.onError(Throwable(task.exception))
                    }
                }
        }
    }

}