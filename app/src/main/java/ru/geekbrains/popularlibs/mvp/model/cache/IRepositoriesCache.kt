package ru.geekbrains.popularlibs.mvp.model.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibs.mvp.model.api.IDataSource
import ru.geekbrains.popularlibs.mvp.model.entity.GithubRepository
import ru.geekbrains.popularlibs.mvp.model.entity.GithubUser
import ru.geekbrains.popularlibs.mvp.model.entity.room.db.Database
import ru.geekbrains.popularlibs.mvp.model.network.NetworkStatus

interface IRepositoriesCache {
    fun getUserRepositories(user: GithubUser, api: IDataSource, networkStatus: NetworkStatus,
                            database: Database): Single<List<GithubRepository>>
}