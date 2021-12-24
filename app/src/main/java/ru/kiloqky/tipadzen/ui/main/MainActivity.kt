package ru.kiloqky.tipadzen.ui.main

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.kiloqky.tipadzen.R
import ru.kiloqky.tipadzen.databinding.ActivityMainBinding
import ru.kiloqky.tipadzen.navigation.cicerone.MainAppNavigator

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()

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
}