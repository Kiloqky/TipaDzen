package ru.kiloqky.tipadzen.navigation.cicerone.navigationcontroller

import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import ru.kiloqky.tipadzen.navigation.cicerone.MainAppNavigator
import ru.kiloqky.tipadzen.navigation.cicerone.screens.Screens
import timber.log.Timber

class NavigationControllerImpl(
    private val navigatorHolder: NavigatorHolder,
    private val router: Router,
    private val screens: Screens
) : NavigationController {

    override fun openRootScreen() {
        router.newRootScreen(screens.PostsScreen())
    }

    override fun removeNavigator() {
        navigatorHolder.removeNavigator()
    }

    override fun setNavigator(navigator: MainAppNavigator) {
        navigatorHolder.setNavigator(navigator)
    }

    override fun navigateToAddPost() {
        router.navigateTo(screens.AddPostScreen())
    }

    override fun back() {
        router.exit()
    }
}