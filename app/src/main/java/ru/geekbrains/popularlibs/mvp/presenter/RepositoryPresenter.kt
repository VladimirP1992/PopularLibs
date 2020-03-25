package ru.geekbrains.popularlibs.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.popularlibs.mvp.model.entity.GithubRepository
import ru.geekbrains.popularlibs.mvp.view.RepositoryView
import ru.terrakok.cicerone.Router

@InjectViewState
class RepositoryPresenter(val repository: GithubRepository, val router: Router) : MvpPresenter<RepositoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRepo()
    }

    fun loadRepo() {
        viewState.setRepoId(repository.id)
        viewState.setRepoName(repository.name)
        viewState.setRepoForksCount(repository.forksCount.toString())
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }
}