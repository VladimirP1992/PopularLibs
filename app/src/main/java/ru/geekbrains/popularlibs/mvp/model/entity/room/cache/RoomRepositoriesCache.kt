package ru.geekbrains.popularlibs.mvp.model.entity.room.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibs.mvp.model.api.IDataSource
import ru.geekbrains.popularlibs.mvp.model.cache.IRepositoriesCache
import ru.geekbrains.popularlibs.mvp.model.entity.GithubRepository
import ru.geekbrains.popularlibs.mvp.model.entity.GithubUser
import ru.geekbrains.popularlibs.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.popularlibs.mvp.model.entity.room.db.Database
import ru.geekbrains.popularlibs.mvp.model.network.NetworkStatus
import java.lang.RuntimeException

class RoomRepositoriesCache : IRepositoriesCache {
    override fun getUserRepositories(
        user: GithubUser,
        api: IDataSource,
        networkStatus: NetworkStatus,
        database: Database
    ) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUserRepos(user.reposUrl)
                .map { repos ->
                    repos.takeIf { it.isNotEmpty() }?.let {
                        val roomRepos = repos.map { RoomGithubRepository(it.id, it.name, it.forksCount, user.login, it.language) }
                        database.repositoryDao.insert(roomRepos)
                    }
                    repos
                }
        } else {
            Single.create<List<GithubRepository>> { emitter ->
                database.userDao.findByLogin(user.login)?.let { roomUser ->
                    val roomRepos = database.repositoryDao.findForUser(user.login)
                    val repos = roomRepos.map { GithubRepository(it.id, it.name, it.forksCount, it.language ?: "") }
                    emitter.onSuccess(repos)
                } ?: let {
                    emitter.onError(RuntimeException("No such user in cache"))
                }
            }
        }
    }

}