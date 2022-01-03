package ru.kiloqky.tipadzen.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.kiloqky.tipadzen.R
import ru.kiloqky.tipadzen.navigation.cicerone.MainAppNavigator

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: viewModel.openRootScreen()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        viewModel.setNavigator(MainAppNavigator(this, R.id.fragment_container))
    }

    override fun onPause() {
        viewModel.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        viewModel.back()
    }
}