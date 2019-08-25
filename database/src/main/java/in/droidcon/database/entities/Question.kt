package `in`.droidcon.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "question")
data class Question(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int, // 148
    @SerializedName("question")
    @ColumnInfo(name = "question")
    val question: String, // Demo
    @SerializedName("questionType")
    @ColumnInfo(name = "questionType")
    val questionType: String, // Percentage
    @SerializedName("sort")
    @ColumnInfo(name = "sort")
    val sort: Int // 5
)