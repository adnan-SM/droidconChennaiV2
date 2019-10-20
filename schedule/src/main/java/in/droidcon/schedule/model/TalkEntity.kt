package `in`.droidcon.schedule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Hari on 2019-10-12.
 * entity for talks
 */
@Parcelize
data class TalkEntity(

    val talkId: String = "",

    val title: String = "",

    val abstract: String = "",

    val category: String = "",

    val date: String = "",

    val duration: String = "",

    val time: String = "",

    val track: String = "",

    val videoUrl: String? = "",

    val speakerId: List<String> = listOf(),

    val speakerNames: List<String> = listOf()
) : Parcelable