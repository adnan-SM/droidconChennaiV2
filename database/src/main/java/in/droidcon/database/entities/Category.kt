package `in`.droidcon.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity(tableName = "category")
data class Category(
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Long, // 2127
    @SerializedName("items")
    @ColumnInfo(name = "items")
    val items: List<Item>,
    @SerializedName("sort")
    @ColumnInfo(name = "sort")
    val sort: Int, // 3
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String // Language
) {
    @Serializable
    data class Item(
        @SerializedName("id")
        val id: Int, // 10676
        @SerializedName("name")
        val name: String, // Russian
        @SerializedName("sort")
        val sort: Int // 9
    )
}