package `in`.droidcon.chennai.navigation

import `in`.droidcon.base.navigation.MainNavigation
import androidx.navigation.NavController

/**
 * @author Adnan A M
 * @since  26/03/19
 */
class Navigator: MainNavigation {

    private var controller: NavController? = null

    override fun bind(navController: NavController) {
        this.controller = navController
    }

    override fun unbind() {
        controller = null
    }

    override fun getNavController(): NavController? = controller
}