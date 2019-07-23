package `in`.droidcon.chennai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mNavigator: Navigator by inject()

    val navController by lazy {
        findNavController(R.id.nav_host)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
}
