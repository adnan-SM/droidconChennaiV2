package `in`.droidcon.chennai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mNavigator: Navigator by inject()

    val navController by lazy {
        findNavController(R.id.bottomNavFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigation()
    }

    override fun onResume() {
        super.onResume()
        mNavigator.bind(navController)
        bottomNavigation.setupWithNavController(navController)
    }

    override fun onPause() {
        super.onPause()
        mNavigator.unbind()
    }

    private fun setupBottomNavigation() {
        val navController = Navigation.findNavController(this, R.id.bottomNavFragment)
        bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            toolbarTitle.text = when (destination.id) {
                R.id.scheduleFragment -> getString(R.string.schedule)
                R.id.speakersFragment -> getString(R.string.speakers)
                R.id.infoFragment -> getString(R.string.info)
                else -> getString(R.string.app_name)
            }
        }
    }
}
