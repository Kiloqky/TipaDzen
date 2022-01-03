package ru.kiloqky.tipadzen.ui.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.kiloqky.tipadzen.R
import ru.kiloqky.tipadzen.ui.main.MainActivity

open class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    open val menuRes by lazy {
        R.menu.menu_main
    }
    open val actionBarTitleRes = R.string.app_name

    open val isBackBtnVisible = false

    private val mainActivity: MainActivity
        get() = activity as MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.supportActionBar?.apply {
            setHasOptionsMenu(menuRes != R.menu.menu_main)
            setTitle(actionBarTitleRes)
            setDisplayHomeAsUpEnabled(isBackBtnVisible)
        }
    }

    fun showError(message: String?) {
        Snackbar.make(requireView(), message ?: getString(R.string.error_unknown), 5000).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(menuRes, menu)
    }
}