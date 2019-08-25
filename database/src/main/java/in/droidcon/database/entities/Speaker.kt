package `in`.droidcon.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity(tableName = "speaker")
data class Speaker(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: String, // 00000000-0000-0000-0000-000000000000
    @SerializedName("bio")
    @ColumnInfo(name = "bio")
    val bio: String, // Award-winning zombie specialist. Reader. Twitter fan. Total introvert. Internet evangelist. Thinker. Beer junkie. Food advocate.
    @SerializedName("categoryItems")
    @ColumnInfo(name = "categoryItems")//TODO: FK
    val categoryItems: List<Long>,
    @SerializedName("firstName")
    @ColumnInfo(name = "firstName")
    val firstName: String, // Sophia
    @SerializedName("fullName")
    @ColumnInfo(name = "fullName")
    val fullName: String, // Sophia Test
    @SerializedName("isTopSpeaker")
    @ColumnInfo(name = "isTopSpeaker")
    val isTopSpeaker: Boolean, // true
    @SerializedName("lastName")
    @ColumnInfo(name = "lastName")
    val lastName: String, // Test
    @SerializedName("links")
    @ColumnInfo(name = "links")
    val links: List<Link>,
    @SerializedName("profilePicture")
    @ColumnInfo(name = "profilePicture")
    val profilePicture: String, // https://sessionize.com/image?f=ceb219e8ad753d8daae91f41aa533d61,400,400,True,False,test0.jpg
    @SerializedName("questionAnswers")
    @ColumnInfo(name = "questionAnswers")
    val questionAnswers: List<Any>,
    @SerializedName("sessions")
    @ColumnInfo(name = "sessions")//TODO: FK
    val sessions: List<String>,
    @SerializedName("tagLine")
    @ColumnInfo(name = "tagLine")
    val tagLine: String // CEO / Contoso
) {
    @Serializable
    data class Link(
        @SerializedName("linkType")
        val linkType: String, // Twitter
        @SerializedName("title")
        val title: String, // Twitter
        @SerializedName("url")
        val url: String // https://twitter.com/sessionizecom
    )
}