package `in`.droidcon.speakers.remote

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "speakers")
data class SpeakerModel(
    @SerializedName("id")
    val id: String,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("fullName")
    val fullName: String,

    @SerializedName("bio")
    val bio: String,

    @SerializedName("tagLine")
    val tagLine: String,

    @SerializedName("profilePicture")
    val profilePicture: String,

    @SerializedName("sessions")
    @Embedded
    val sessions: List<Session>,

    @SerializedName("links")
    @Embedded
    val links: List<Link>,

    @SerializedName("isTopSpeaker")
    val isTopSpeaker: Boolean
)

data class Session(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)

data class Link(
    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("linkType")
    val linkType: String
)