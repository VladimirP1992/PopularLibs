package ru.geekbrains.popularlibs.navigation

import ru.geekbrains.popularlibs.mvp.model.entity.GithubRepository
import ru.geekbrains.popularlibs.ui.fragments.RepositoriesFragment
import ru.geekbrains.popularlibs.ui.fragments.RepositoryFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class RepositoriesScreen() : SupportAppScreen() {
        override fun getFragment() = RepositoriesFragment.newInstance()
    }

    class RepositoryScreen(val repository: GithubRepository) : SupportAppScreen() {
        override fun getFragment() = RepositoryFragment.newInstance(repository)
    }

}