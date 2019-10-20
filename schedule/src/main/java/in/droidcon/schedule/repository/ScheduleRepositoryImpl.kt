package `in`.droidcon.schedule.repository

import `in`.droidcon.base.extension.getDayOneSchedule
import `in`.droidcon.base.extension.getDayTwoSchedule
import `in`.droidcon.base.extension.querySpeakers
import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.model.SpeakerEntity
import `in`.droidcon.schedule.model.ScheduleEntity
import `in`.droidcon.schedule.model.ScheduleResponse
import `in`.droidcon.schedule.model.TalkEntity
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

/**
 * Created by Hari on 2019-10-12.
 * Schedule repository implementation
 */
class ScheduleRepositoryImpl(private val fireStore: FirebaseFirestore): ScheduleRepository {

    override fun querySpeakers(talkId: String): Single<List<SpeakerEntity>> {
        return Single.create { emitter ->
            fireStore.querySpeakers(talkId)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            val list: List<SpeakerEntity> = result.toObjects(SpeakerEntity::class.java)
                            emitter.onSuccess(list)
                        } ?: emitter.onError(Throwable("Speakers not found"))
                    } else {
                        emitter.onError(Throwable(task.exception))
                    }
                }
        }
    }

    override fun getDayOneSchedule(): Single<List<ScheduleEntity>> {
        return Single.create { emitter ->
            fireStore.getDayOneSchedule()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            val taskList: MutableList<Task<DocumentSnapshot>> = mutableListOf()
                            val scheduleList: MutableList<ScheduleEntity> = mutableListOf()
                            val responselist: List<ScheduleResponse> = result.toObjects(ScheduleResponse::class.java)

                            responselist.forEach { schedule ->
                                schedule.talks.forEach { ref -> taskList.add(ref.get()) }
                                Tasks.whenAllSuccess<Any>(taskList).addOnSuccessListener { list ->
                                    val mappedTalks = list.map { (it as DocumentSnapshot).toObject(TalkEntity::class.java) }
                                    scheduleList.add(ScheduleEntity(schedule.time, mappedTalks))
                                    emitter.onSuccess(scheduleList)
                                }
                            }

                        } ?: emitter.onError(Throwable("Schedule not found"))
                    } else {
                        emitter.onError(Throwable(task.exception))
                    }
                }
        }
    }

    override fun getDayTwoSchedule(): Single<List<ScheduleEntity>> {
        return Single.create { emitter ->
            fireStore.getDayTwoSchedule()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            val list: List<ScheduleEntity> = result.toObjects(ScheduleEntity::class.java)
                            emitter.onSuccess(list)
                        } ?: emitter.onError(Throwable("Schedule not found"))
                    } else {
                        emitter.onError(Throwable(task.exception))
                    }
                }
        }
    }


}