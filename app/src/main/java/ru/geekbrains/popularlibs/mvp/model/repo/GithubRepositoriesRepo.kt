package ru.geekbrains.popularlibs.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.popularlibs.mvp.model.api.IDataSource

class GithubRepositoriesRepo (val api: IDataSource){
    fun getRepos(reposUrl: String) = api.getRepos(reposUrl).subscribeOn(Schedulers.io())
}