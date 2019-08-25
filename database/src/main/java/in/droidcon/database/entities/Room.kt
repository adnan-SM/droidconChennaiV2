package `in`.droidcon.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "room")
data class Room(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int, // 217
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String, // Restaurant
    @SerializedName("sort")
    @ColumnInfo(name = "sort")
    val sort: Int // 2
)