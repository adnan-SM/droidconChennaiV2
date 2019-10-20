package `in`.droidcon.base.view

import `in`.droidcon.base.R
import `in`.droidcon.base.common.GlideApp
import `in`.droidcon.base.core.RoundedBottomSheetDialogFragment
import `in`.droidcon.base.epoxy.controller.GridDetailController
import `in`.droidcon.base.model.GridItem
import android.content.Intent
import android.graphics.Outline
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import kotlinx.android.synthetic.main.layout_grid_detail.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * Created by Hari on 2019-08-23.
 * Common grid bottom sheet for detail view
 */
class GridDetailBottomSheet : RoundedBottomSheetDialogFragment(), GridDetailController.GridDetailCallbacks {

    private val detailController: GridDetailController by inject { parametersOf(this) }
    lateinit var skeleton: RecyclerViewSkeletonScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.layout_grid_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        infoList.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = detailController.adapter
        }
    }

    private fun setupImage(path: String) {
        setupRoundedImage()
        GlideApp.with(avatarImage.context)
            .load(path)
            .placeholder(R.color.skeleton)
            .error(R.color.skeleton)
            .into(avatarImage)
    }

    private fun setupRoundedImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            avatarImage.outlineProvider = object : ViewOutlineProvider() {
                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun getOutline(view: View?, outline: Outline?) {
                    outline?.setRoundRect(0, 0, view!!.width, (view.height + 25F).toInt(), 25F)
                }
            }
            avatarImage.clipToOutline = true
        }
    }

    private fun getData() {
        arguments?.let {
            val gridItem: GridItem = it.getParcelable("gridItem")
            gridItem.apply {
                setupImage(gridImg)
                detailController.setData(this)
            }
        }
    }

    override fun onTwitterButtonClicked(twitterHandle: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/$twitterHandle")))
    }

    override fun onWebsiteButtonClicked(websiteAddress: String) {
        if (websiteAddress.isNotEmpty()) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteAddress))
            startActivity(browserIntent)
        } else {
            Toast.makeText(requireContext(), "Unable to find URL", Toast.LENGTH_LONG).show()
        }
    }
}