package ru.geekbrains.popularlibs.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.popularlibs.mvp.model.api.IDataSource
import ru.geekbrains.popularlibs.mvp.model.cache.IGithubRepositoriesCache
import ru.geekbrains.popularlibs.mvp.model.entity.GithubUser
import ru.geekbrains.popularlibs.mvp.model.network.NetworkStatus

class GithubRepositoriesRepo(val api: IDataSource, val networkStatus: NetworkStatus, val cache: IGithubRepositoriesCache) {

    fun getUserRepositories(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUserRepos(user.reposUrl)
                .flatMap { repos ->
                    cache.putUserRepos(user, repos).toSingleDefault(repos)
                }
        } else {
            cache.getUserRepos(user)
        }
    }.subscribeOn(Schedulers.io())

}