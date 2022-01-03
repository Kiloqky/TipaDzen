package ru.kiloqky.tipadzen.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.kiloqky.tipadzen.navigation.cicerone.MainAppNavigator
import ru.kiloqky.tipadzen.navigation.cicerone.navigationcontroller.NavigationController
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navController: NavigationController
) : ViewModel() {

    fun openRootScreen() = navController.openRootScreen()

    fun setNavigator(navigator: MainAppNavigator) = navController.setNavigator(navigator)

    fun removeNavigator() = navController.removeNavigator()

    fun back() = navController.back()
}