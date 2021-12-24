package ru.kiloqky.tipadzen.ui.main.interactor

import ru.kiloqky.tipadzen.R
import ru.kiloqky.tipadzen.navigation.cicerone.MainAppNavigator
import ru.kiloqky.tipadzen.navigation.cicerone.navigationcontroller.NavigationController
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val navigationController: NavigationController
) {
    fun openRootScreen(){
        navigationController.openRootScreen()
    }

    fun setNavigator(navigator: MainAppNavigator) {
        navigationController.setNavigator(navigator)
    }

    fun removeNavigator() {
        navigationController.removeNavigator()
    }
}