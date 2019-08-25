package `in`.droidcon.database.entities
import com.google.gson.annotations.SerializedName


data class EventDetails(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("questions")
    val questions: List<Question>,
    @SerializedName("rooms")
    val rooms: List<Room>,
    @SerializedName("sessions")
    val sessions: List<Session>,
    @SerializedName("speakers")
    val speakers: List<Speaker>
)