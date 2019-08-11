package `in`.droidcon.info.team.presentation

import `in`.droidcon.base.core.BaseViewModel
import `in`.droidcon.base.event.Event
import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.state.ResultState
import `in`.droidcon.info.team.domain.GetAllTeamMembers
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

/**
 * Created by Hari on 2019-08-10.
 * View model for team
 */
class TeamListViewModel(private val getTeam: GetAllTeamMembers): BaseViewModel() {

    private val teamListState = MutableLiveData<Event<ResultState<List<GridItem>, String>>>()

    fun getTeamListState(): LiveData<Event<ResultState<List<GridItem>, String>>> = teamListState

    init {
        getTeamList()
    }

    private fun getTeamList() {
        getTeam.execute()
            .doOnSubscribe {
                teamListState.postValue(Event(ResultState.Loading))
                Timber.i("Firestore loading")
            }
            .subscribe({ list ->
                teamListState.postValue(Event(ResultState.Success(list)))
                Timber.i("Firestore fetching team successful")
            }, { throwable ->
                val errorMessage = throwable.message ?: ERROR_MESSAGE
                teamListState.postValue(Event(ResultState.Failed(errorMessage)))
                Timber.i("Firestore fetching team failed")
            })
            .addTo(disposables)
    }
}