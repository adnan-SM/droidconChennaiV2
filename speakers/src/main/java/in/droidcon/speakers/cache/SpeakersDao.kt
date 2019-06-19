package `in`.droidcon.speakers.cache

import `in`.droidcon.speakers.remote.SpeakerModel
import androidx.room.Dao
import androidx.room.Query

@Dao
interface SpeakersDao {

    @Query("select * from speakers")
    fun getAllSpeakers(): List<SpeakerModel>

}