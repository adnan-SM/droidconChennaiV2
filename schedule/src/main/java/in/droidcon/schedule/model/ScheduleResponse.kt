package `in`.droidcon.schedule.model

import com.google.firebase.firestore.DocumentReference

/**
 * Created by Hari on 2019-10-12.
 * firestore response of schedule
 */
data class ScheduleResponse(
    val time: String = "",
    val talks: List<DocumentReference> = listOf()
)