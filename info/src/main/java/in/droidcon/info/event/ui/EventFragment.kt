package `in`.droidcon.info.event.ui


import `in`.droidcon.base.event.EventObserver
import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.state.ResultState
import `in`.droidcon.info.R
import `in`.droidcon.info.common.epoxy.controller.InfoController
import `in`.droidcon.info.common.model.EventEntity
import `in`.droidcon.info.event.presentation.EventListViewModel
import `in`.droidcon.info.team.model.TeamEntity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_event.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 *
 */
class EventFragment : Fragment(), InfoController.InfoCallbacks {

    private val infoController: InfoController by inject { parametersOf(this) }
    private val eventViewModel: EventListViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_event, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupRecyclerView(view)
    }

    private fun setupRecyclerView(view: View) {
        view.listView.adapter = infoController.adapter
    }

    private fun observeViewModel() {
        eventViewModel.getEventListState().observe(viewLifecycleOwner, EventObserver { result ->
            when (result) {
                is ResultState.Loading -> {}
                is ResultState.Success<List<EventEntity>> -> {
                    infoController.setData(result.result)
                }
                is ResultState.Failed<String> -> { }
            }
        })
    }

    override fun onCallActionClicked(number: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUrlClicked(url: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapClicked(address: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCopyClicked(copyItem: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
