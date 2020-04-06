package ru.geekbrains.popularlibs.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.popularlibs.mvp.model.api.IDataSource
import ru.geekbrains.popularlibs.mvp.model.cache.IRepositoriesCache
import ru.geekbrains.popularlibs.mvp.model.entity.GithubUser
import ru.geekbrains.popularlibs.mvp.model.entity.room.db.Database
import ru.geekbrains.popularlibs.mvp.model.network.NetworkStatus

//TODO: Практическое задание 1 - вытащить кэширование в отдельный класс RoomRepositoriesCache и внедрить его сюда через интфейс IRepositoriesCache
class GithubRepositoriesRepo(val api: IDataSource, val networkStatus: NetworkStatus, val database: Database, val repositoriesCache: IRepositoriesCache) {
    fun getUserRepositories(user: GithubUser) = repositoriesCache.getUserRepositories(user, api, networkStatus, database).subscribeOn(Schedulers.io())
}