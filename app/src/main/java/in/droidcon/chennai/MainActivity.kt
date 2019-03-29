package `in`.droidcon.chennai

import `in`.droidcon.speakers.presentation.ui.SpeakerFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private var mFragment: SpeakerFragment? = null
    private val mNavigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        if (mFragment == null) {
            val ft = fm.beginTransaction()
            mFragment = SpeakerFragment()
            ft.add(R.id.fragment_container, mFragment as Fragment, "myFragmentTag")
            ft.commit()
        }
    }

    override fun onResume() {
        super.onResume()
        mNavigator.bind(findNavController(R.id.navigation_home))
    }

    override fun onPause() {
        super.onPause()
        mNavigator.unbind()
    }
}
