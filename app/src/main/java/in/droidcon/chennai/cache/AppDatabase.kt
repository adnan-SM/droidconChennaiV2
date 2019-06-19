package `in`.droidcon.chennai.cache

import `in`.droidcon.speakers.cache.SpeakersDao
import `in`.droidcon.speakers.remote.SpeakerModel
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SpeakerModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun speakersDao(): SpeakersDao
}