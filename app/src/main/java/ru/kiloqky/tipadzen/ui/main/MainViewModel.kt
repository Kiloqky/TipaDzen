package ru.kiloqky.tipadzen.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.kiloqky.tipadzen.navigation.cicerone.MainAppNavigator
import ru.kiloqky.tipadzen.ui.main.interactor.MainInteractor
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor: MainInteractor
) : ViewModel() {

    fun openRootScreen() {
        interactor.openRootScreen()
    }

    fun setNavigator(navigator: MainAppNavigator) {
        interactor.setNavigator(navigator)
    }

    fun removeNavigator() {
        interactor.removeNavigator()
    }
}