package ru.kiloqky.tipadzen.navigation.cicerone.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kiloqky.tipadzen.ui.posts.PostsFragment

class ScreensImpl : Screens {
    override fun PostsScreen() = FragmentScreen {
        PostsFragment.newInstance()
    }
}