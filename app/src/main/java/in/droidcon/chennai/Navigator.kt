package `in`.droidcon.chennai

import androidx.navigation.NavController

/**
 * @author Adnan A M
 * @since  26/03/19
 */
class Navigator {

    private var navController: NavController? = null

    fun openSpeakerDetails(speakerId: String) { }

    fun bind(navController: NavController) {
        this.navController = navController
    }

    fun unbind() {
        navController = null
    }

}