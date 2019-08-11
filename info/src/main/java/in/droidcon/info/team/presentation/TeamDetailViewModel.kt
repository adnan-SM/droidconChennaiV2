package `in`.droidcon.info.team.presentation

import `in`.droidcon.base.core.BaseViewModel
import `in`.droidcon.base.event.Event
import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.state.ResultState
import `in`.droidcon.info.team.domain.GetOneTeamMember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

/**
 * Created by Hari on 2019-08-10.
 * team detail view model
 */
class TeamDetailViewModel(private val getOneTeamMember: GetOneTeamMember): BaseViewModel() {

    private val teamMemberState = MutableLiveData<Event<ResultState<GridItem, String>>>()

    fun getTeamMemberState(): LiveData<Event<ResultState<GridItem, String>>> = teamMemberState

    fun getOneTeamMember(id: String) {
        getOneTeamMember.execute(GetOneTeamMember.Companion.Params(id))
            .doOnSubscribe {
                teamMemberState.postValue(Event(ResultState.Loading))
                Timber.i("Firestore loading")
            }
            .subscribe({ teamMember ->
                teamMemberState.postValue(Event(ResultState.Success(teamMember)))
                Timber.i("Firestore fetching team successful")
            }, { throwable ->
                val errorMessage = throwable.message ?: ERROR_MESSAGE
                teamMemberState.postValue(Event(ResultState.Failed(errorMessage)))
                Timber.i("Firestore fetching team failed")
            })
            .addTo(disposables)
    }
}