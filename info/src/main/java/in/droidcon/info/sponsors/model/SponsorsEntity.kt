package `in`.droidcon.info.sponsors.model

import androidx.annotation.Keep

/**
 * Created by Hari on 2019-08-22.
 * Data class for sponsors
 */
@Keep
data class SponsorsEntity(

    val id: String = "",

    val name: String = "",

    val blob: String? = "",

    val country: String = "",

    val website: String = "",

    val avatar: String = "",

    val role: String = "",

    val order: String = ""

)