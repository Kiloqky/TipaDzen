package ru.kiloqky.tipadzen.navigation.cicerone.navigationcontroller

import ru.kiloqky.tipadzen.navigation.cicerone.MainAppNavigator

interface NavigationController {
    fun openRootScreen()
    fun removeNavigator()
    fun setNavigator(navigator: MainAppNavigator)
    fun navigateToAddPost()
    fun back()
}