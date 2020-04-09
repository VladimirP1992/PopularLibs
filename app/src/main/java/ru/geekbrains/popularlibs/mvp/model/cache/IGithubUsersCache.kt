package ru.geekbrains.popularlibs.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popularlibs.mvp.model.entity.GithubUser

interface IGithubUsersCache {
    fun getUser(username: String): Single<GithubUser>
    fun putUser(user: GithubUser): Completable
}