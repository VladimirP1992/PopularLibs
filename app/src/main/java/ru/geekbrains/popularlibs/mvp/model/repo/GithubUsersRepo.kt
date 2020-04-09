package ru.geekbrains.popularlibs.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.popularlibs.mvp.model.api.IDataSource
import ru.geekbrains.popularlibs.mvp.model.cache.IGithubUsersCache
import ru.geekbrains.popularlibs.mvp.model.network.NetworkStatus

class GithubUsersRepo(val api: IDataSource, val networkStatus: NetworkStatus, val cache: IGithubUsersCache) {
    fun getUser(username: String) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUser(username)
                .flatMap { user ->
                    return@flatMap cache.putUser(user).toSingleDefault(user)
                }
        } else {
            cache.getUser(username)
        }
    }.subscribeOn(Schedulers.io())
}
