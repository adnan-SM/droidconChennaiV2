package `in`.droidcon.speakers.remote

import io.reactivex.Single
import retrofit2.http.GET

interface SpeakerService {

    @GET("speakers")
    fun getSpeakers(): Single<List<SpeakerModel>>

}