package `in`.droidcon.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity(tableName = "session")
data class Session(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: String, // 14021
    @SerializedName("categoryItems")
    @ColumnInfo(name = "categoryItems")//TODO: FK
    val categoryItems: List<Int>,
    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String, // Usually, you would find a session description here. But, this is not a real session submission, so description is missing. Ha!
    @SerializedName("endsAt")
    @ColumnInfo(name = "endsAt")
    val endsAt: String, // 2017-05-28T12:30:00Z
    @SerializedName("isPlenumSession")
    @ColumnInfo(name = "isPlenumSession")
    val isPlenumSession: Boolean, // false
    @SerializedName("isServiceSession")
    @ColumnInfo(name = "isServiceSession")
    val isServiceSession: Boolean, // false
    @SerializedName("questionAnswers")
    @ColumnInfo(name = "questionAnswers")
    val questionAnswers: List<QuestionAnswer>,
    @SerializedName("roomId")
    @ColumnInfo(name = "roomId")//TODO: FK
    val roomId: Int, // 216
    @SerializedName("speakers")
    @ColumnInfo(name = "speakers")//TODO: FK
    val speakers: List<String>,
    @SerializedName("startsAt")
    @ColumnInfo(name = "startsAt")
    val startsAt: String, // 2017-05-28T11:30:00Z
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String // Olivia's Session
) {
    @Serializable
    data class QuestionAnswer(
        @SerializedName("answerValue")
        val answerValue: String, // 56
        @SerializedName("questionId")
        val questionId: Int // 148
    )
}