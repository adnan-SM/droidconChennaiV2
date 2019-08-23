package `in`.droidcon.base.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Hari on 2019-08-10.
 * Data class for grid item
 */
@Parcelize
data class GridItem(

    val gridId: String,

    val gridName: String,

    val gridOrg: String,

    val gridLocation: String,

    val gridOneLiner: String?,

    val gridHandle: String?,

    val gridWebsite: String?,

    val gridBlurbs: String?,

    val gridImg: String,

    val talkId: List<String>?

) : Parcelable