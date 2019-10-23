package `in`.droidcon.home

import `in`.droidcon.base.core.BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import timber.log.Timber

/**
 * A simple [BaseFragment] subclass.
 *
 */
class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomNavigation(view)
    }

    private fun setupBottomNavigation(view: View) {
        val navController = Navigation.findNavController(requireActivity(), R.id.bottomNavFragment)
        view.bottomNavigation.setupWithNavController(navController)
    }
}
