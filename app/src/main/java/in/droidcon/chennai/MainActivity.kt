package `in`.droidcon.chennai

import `in`.droidcon.base.navigation.MainNavigation
import `in`.droidcon.schedule.navigation.ScheduleNavigation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navigator: MainNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator.bind(findNavController(R.id.navHostFragment))
    }

}
