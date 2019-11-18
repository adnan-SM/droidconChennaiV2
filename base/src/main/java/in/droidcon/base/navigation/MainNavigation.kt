package `in`.droidcon.base.navigation

import androidx.navigation.NavController

/**
 * Created by Hari on 2019-10-14.
 * Main navigation
 */
interface MainNavigation {

    fun bind(navController: NavController)

    fun unbind()

    fun getNavController(): NavController?
}