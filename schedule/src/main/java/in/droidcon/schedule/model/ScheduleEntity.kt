package `in`.droidcon.schedule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Hari on 2019-10-12.
 * DTO for scheule
 */
@Parcelize
data class ScheduleEntity(
    val order: Int = 0,
    val time: String = "",
    val talks: List<TalkEntity?> = listOf(),
    val event: String = ""
) : Parcelable