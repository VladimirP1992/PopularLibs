package ru.geekbrains.popularlibs.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.popularlibs.mvp.model.entity.GithubRepository
import ru.geekbrains.popularlibs.mvp.view.RepositoryView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class RepositoryPresenter(val githubRepository: GithubRepository) : MvpPresenter<RepositoryView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setId(githubRepository.id)
        viewState.setTitle(githubRepository.name)
        viewState.setForksCount(githubRepository.forksCount.toString())
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }
}