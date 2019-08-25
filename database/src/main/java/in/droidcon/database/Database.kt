package `in`.droidcon.database

import `in`.droidcon.database.entities.*
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Speaker::class, Session::class, Room::class, Question::class, Category::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {
    abstract fun eventDAO(): EventDAO
}