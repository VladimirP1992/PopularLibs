package ru.geekbrains.popularlibs.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.popularlibs.mvp.view.MainView
import ru.geekbrains.popularlibs.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainPresenter() : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        router.replaceScreen(Screens.RepositoriesScreen())
    }

    fun backClicked() {
        router.exit()
    }
}
