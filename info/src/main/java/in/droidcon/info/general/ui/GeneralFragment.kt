package `in`.droidcon.info.general.ui


import `in`.droidcon.base.state.ResultState
import `in`.droidcon.info.InfoFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import `in`.droidcon.info.R
import `in`.droidcon.info.common.epoxy.controller.InfoController
import `in`.droidcon.info.common.model.InfoEntity
import `in`.droidcon.info.common.presentation.InfoViewModel
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_event.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 *
 */
class GeneralFragment : Fragment(), InfoController.InfoCallbacks {

    private val infoController: InfoController by inject { parametersOf(this) }
    private lateinit var eventViewModel: InfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_general, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventViewModel = (parentFragment as InfoFragment).infoViewModel
        observeViewModel()
        setupRecyclerView(view)
    }

    private fun setupRecyclerView(view: View) {
        view.listView.adapter = infoController.adapter
    }

    private fun observeViewModel() {
        eventViewModel.getGeneralListState().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is ResultState.Loading -> {
                }
                is ResultState.Success<List<InfoEntity>> -> {
                    infoController.setData(result.result)
                }
                is ResultState.Failed<String> -> {
                }
            }
        })
    }

    override fun onCallActionClicked(number: String?) {
        if (!number.isNullOrEmpty()) {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:$number")
            startActivity(callIntent)
        } else {
            Toast.makeText(
                requireContext(),
                "No number found. Please call manually",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onUrlClicked(url: String?) {
        if (!url.isNullOrEmpty()) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        } else {
            Toast.makeText(requireContext(), "Unable to find URL", Toast.LENGTH_LONG).show()
        }
    }

    override fun onMapClicked(address: String?) {
        val mapAddress = "http://maps.google.co.in/maps?q=$address"
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapAddress))
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    override fun onCopyClicked(copyItem: String?) {
        val clipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
        val clip = ClipData.newPlainText("DroidconWifiPwd", copyItem)
        clipboard?.primaryClip = clip
        Toast.makeText(requireContext(), "Wifi Password Copied", Toast.LENGTH_LONG).show()
    }

}
