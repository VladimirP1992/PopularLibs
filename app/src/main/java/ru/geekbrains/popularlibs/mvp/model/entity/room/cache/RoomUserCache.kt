package ru.geekbrains.popularlibs.mvp.model.entity.room.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibs.mvp.model.api.IDataSource
import ru.geekbrains.popularlibs.mvp.model.cache.IUserCache
import ru.geekbrains.popularlibs.mvp.model.entity.GithubUser
import ru.geekbrains.popularlibs.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.popularlibs.mvp.model.entity.room.db.Database
import ru.geekbrains.popularlibs.mvp.model.network.NetworkStatus
import java.lang.RuntimeException

class RoomUserCache : IUserCache {
    override fun getUser(
        userName: String,
        api: IDataSource,
        networkStatus: NetworkStatus,
        database: Database
    ) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUser(userName)
                .map { user ->
                    val roomUser = database.userDao.findByLogin(userName)?.apply {
                        avatarUrl = user.avatarUrl
                        reposUrl = user.reposUrl
                    } ?: RoomGithubUser(user.login, user.avatarUrl, user.reposUrl)
                    database.userDao.insert(roomUser)
                    user
                }
        } else {
            Single.create<GithubUser> { emitter ->
                database.userDao.findByLogin(userName)?.let { roomUser ->
                    emitter.onSuccess(GithubUser(roomUser.login, roomUser.avatarUrl, roomUser.reposUrl))
                } ?: let {
                    emitter.onError(RuntimeException("No such user in cache"))
                }
            }
        }
    }
}