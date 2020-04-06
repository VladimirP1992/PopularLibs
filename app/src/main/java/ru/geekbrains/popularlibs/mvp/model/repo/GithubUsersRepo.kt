package ru.geekbrains.popularlibs.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.popularlibs.mvp.model.api.IDataSource
import ru.geekbrains.popularlibs.mvp.model.cache.IUserCache
import ru.geekbrains.popularlibs.mvp.model.entity.room.db.Database
import ru.geekbrains.popularlibs.mvp.model.network.NetworkStatus

class GithubUsersRepo(val api: IDataSource, val networkStatus: NetworkStatus, val database: Database, val userCache: IUserCache) {
    fun getUser(username: String) = userCache.getUser(username, api, networkStatus, database).subscribeOn(Schedulers.io())
}