package `in`.droidcon.schedule.presentation

import `in`.droidcon.base.core.BaseViewModel
import `in`.droidcon.base.event.Event
import `in`.droidcon.base.model.SpeakerEntity
import `in`.droidcon.base.state.ResultState
import `in`.droidcon.schedule.domain.GetDayOneSchedule
import `in`.droidcon.schedule.domain.GetDayTwoSchedule
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
    private val getDayOneSchedule: GetDayOneSchedule,
    private val getDayTwoSchedule: GetDayTwoSchedule,
    private val querySpeaker: QuerySpeakers
) : BaseViewModel() {

    private val dayOneListState = MutableLiveData<Event<ResultState<List<ScheduleEntity>, String>>>()
    private val dayTwoListState = MutableLiveData<Event<ResultState<List<ScheduleEntity>, String>>>()
    private val speakerListState = MutableLiveData<Event<ResultState<List<SpeakerEntity>, String>>>()

    fun getDayOneListState(): LiveData<Event<ResultState<List<ScheduleEntity>, String>>> = dayOneListState
    fun getDayTwoListState(): LiveData<Event<ResultState<List<ScheduleEntity>, String>>> = dayTwoListState
    fun getSpeakerListState(): LiveData<Event<ResultState<List<SpeakerEntity>, String>>> = speakerListState

    init {
        getDayOneList()
        getDayTwoList()
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

    private fun getDayOneList() {
        getDayOneSchedule.execute()
            .doOnSubscribe {
                dayOneListState.postValue(Event(ResultState.Loading))
                Timber.i("Firestore loading")
            }
            .subscribe({ list ->
                dayOneListState.postValue(Event(ResultState.Success(list)))
                Timber.i("Firestore fetching schedule successful")
            }, { throwable ->
                val errorMessage = throwable.message ?: ERROR_MESSAGE
                dayOneListState.postValue(Event(ResultState.Failed(errorMessage)))
                Timber.i("Firestore fetching schedule failed")
            })
            .addTo(disposables)
    }

    private fun getDayTwoList() {
        getDayTwoSchedule.execute()
            .doOnSubscribe {
                dayTwoListState.postValue(Event(ResultState.Loading))
                Timber.i("Firestore loading")
            }
            .subscribe({ list ->
                dayTwoListState.postValue(Event(ResultState.Success(list)))
                Timber.i("Firestore fetching schedule successful")
            }, { throwable ->
                val errorMessage = throwable.message ?: ERROR_MESSAGE
                dayTwoListState.postValue(Event(ResultState.Failed(errorMessage)))
                Timber.i("Firestore fetching schedule failed")
            })
            .addTo(disposables)
    }

}