package `in`.droidcon.schedule.presentation

import `in`.droidcon.base.core.BaseViewModel
import `in`.droidcon.base.event.Event
import `in`.droidcon.base.model.SpeakerEntity
import `in`.droidcon.base.state.ResultState
import `in`.droidcon.schedule.domain.GetAllSchedule
import `in`.droidcon.schedule.domain.QuerySpeakers
import `in`.droidcon.schedule.model.ScheduleEntity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

/**
 * Created Hari on 2019-10-12.
 * Common viewmodel for schedule
 */
class ScheduleViewModel(
    private val getAllSchedule: GetAllSchedule,
    private val querySpeaker: QuerySpeakers
) : BaseViewModel() {

    private val scheduleListState = MutableLiveData<Event<ResultState<List<ScheduleEntity>, String>>>()
    private val speakerListState = MutableLiveData<Event<ResultState<List<SpeakerEntity>, String>>>()

    fun getScheduleListState(): LiveData<Event<ResultState<List<ScheduleEntity>, String>>> = scheduleListState
    fun getSpeakerListState(): LiveData<Event<ResultState<List<SpeakerEntity>, String>>> = speakerListState

    init {
        getSchedlueList()
    }

    fun querySpeaker(talkId: String) {
        querySpeaker.execute(QuerySpeakers.Companion.Params(talkId))
            .doOnSubscribe {
                speakerListState.postValue(Event(ResultState.Loading))
                Timber.i("Firestore loading")
            }
            .subscribe({ list ->
                speakerListState.postValue(Event(ResultState.Success(list)))
                Timber.i("Firestore fetching schedule successful")
            }, { throwable ->
                val errorMessage = throwable.message ?: ERROR_MESSAGE
                speakerListState.postValue(Event(ResultState.Failed(errorMessage)))
                Timber.i("Firestore fetching speakers failed")
            })
            .addTo(disposables)
    }

    private fun getSchedlueList() {
        getAllSchedule.execute()
            .doOnSubscribe {
                scheduleListState.postValue(Event(ResultState.Loading))
                Timber.i("Firestore loading")
            }
            .subscribe({ list ->
                scheduleListState.postValue(Event(ResultState.Success(list)))
                Timber.i("Firestore fetching schedule successful")
            }, { throwable ->
                val errorMessage = throwable.message ?: ERROR_MESSAGE
                scheduleListState.postValue(Event(ResultState.Failed(errorMessage)))
                Timber.i("Firestore fetching schedule failed")
            })
            .addTo(disposables)
    }

}