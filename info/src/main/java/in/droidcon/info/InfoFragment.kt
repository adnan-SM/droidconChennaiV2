package `in`.droidcon.info


import `in`.droidcon.info.common.presentation.InfoViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.fragment_info.view.*
import kotlinx.android.synthetic.main.fragment_info.view.toolbarTitle
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 *
 */
class InfoFragment : Fragment() {

    val infoViewModel: InfoViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_info, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitle.text = getString(R.string.info)
        val viewPager: ViewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = InfoPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

}
