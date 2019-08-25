package `in`.droidcon.database

import `in`.droidcon.database.entities.Room
import `in`.droidcon.database.entities.Session
import `in`.droidcon.database.entities.Speaker
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface EventDAO {
    //Sepaker
    @Query("select * from speaker")
    fun getAllSpeakers(): Single<List<Speaker>>

    @Query("select * from speaker where id like :speakerId")
    fun getSpeaker(speakerId: String): Single<Speaker>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpeakers(speakers: List<Speaker>): Completable

    //Session
    @Query("select * from session")
    fun getAllSessions(): Single<List<Session>>

    @Query("select * from session where id in (:sessionIds)")
    fun getSessions(sessionIds: Array<String>): Single<List<Session>>

    @Query("select * from session where id like :sessionId")
    fun getSession(sessionId: String): Single<Session>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSessions(sessions: List<Session>): Completable

    //Room
    @Query("select * from room")
    fun getAllRooms(): Single<List<Room>>

    @Query("select * from room where id like :roomId")
    fun getRoom(roomId: String): Single<Session>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRooms(sessions: List<Room>): Completable

    companion object {
        fun newInstance(database: Database): EventDAO = database.eventDAO()
    }
}