package ru.geekbrains.popularlibs.mvp.presenter

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.popularlibs.mvp.model.entity.GithubRepository
import ru.geekbrains.popularlibs.mvp.model.repo.GithubRepositoriesRepo
import ru.geekbrains.popularlibs.mvp.presenter.list.IRepositoryListPresenter
import ru.geekbrains.popularlibs.mvp.view.RepositoriesView
import ru.geekbrains.popularlibs.mvp.view.list.RepositoryItemView
import ru.geekbrains.popularlibs.navigation.Screens
import ru.terrakok.cicerone.Router

@InjectViewState
class RepositoriesPresenter(val repositoriesRepo: GithubRepositoriesRepo, val router: Router) : MvpPresenter<RepositoriesView>() {

    class RepositoryListPresenter : IRepositoryListPresenter {
        val repositories = mutableListOf<GithubRepository>()
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun getCount() = repositories.size

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            view.setTitle(repository.name)
        }
    }

    val repositoryListPresenter = RepositoryListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadRepos()

        repositoryListPresenter.itemClickListener = { itemView ->
            val repository = repositoryListPresenter.repositories[itemView.pos]
            
            router.navigateTo(Screens.RepositoryScreen(repository))
        }
    }

    fun loadRepos() {
        repositoryListPresenter.repositories.clear()
        repositoriesRepo.getRepos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { repos -> repositoryListPresenter.repositories.addAll(repos) },
                { viewState.showUpdateError() },
                { viewState.updateList() })
    }

    fun backClicked() : Boolean {
        router.exit()
        return true
    }
}
