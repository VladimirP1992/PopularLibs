package ru.geekbrains.popularlibs.mvp.model.repo

import io.reactivex.rxjava3.core.Observable
import ru.geekbrains.popularlibs.mvp.model.entity.GithubRepository

class GithubRepositoriesRepo {

    private val repositories = listOf(
        GithubRepository("1", "name1", 100),
        GithubRepository("2", "name2", 200),
        GithubRepository("3", "name3", 300),
        GithubRepository("4", "name4", 400)
    )

    //тут наверрное грузим репозитории из интернета
    private fun loadRepositories() = repositories

    fun getRepos() = Observable.fromCallable {
        return@fromCallable loadRepositories()
    }
}