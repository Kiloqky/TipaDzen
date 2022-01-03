package ru.kiloqky.tipadzen.navigation.cicerone

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.google.android.material.transition.MaterialSharedAxis

const val ANIMATION_DURATION = 300L

class MainAppNavigator(activity: FragmentActivity, @IdRes id: Int) : AppNavigator(activity, id) {

    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment,
    ) {
        currentFragment?.let {
            it.exitTransition =
                MaterialSharedAxis(MaterialSharedAxis.X, true).setDuration(ANIMATION_DURATION)
            it.reenterTransition =
                MaterialSharedAxis(MaterialSharedAxis.X, false).setDuration(ANIMATION_DURATION)
        }
        nextFragment.let {
            it.exitTransition =
                MaterialSharedAxis(MaterialSharedAxis.X, true).setDuration(ANIMATION_DURATION)
            it.reenterTransition =
                MaterialSharedAxis(MaterialSharedAxis.X, false).setDuration(ANIMATION_DURATION)
        }
    }
}
