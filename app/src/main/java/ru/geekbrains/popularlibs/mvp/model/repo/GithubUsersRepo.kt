package ru.geekbrains.popularlibs.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.popularlibs.mvp.model.api.IDataSource

class GithubUsersRepo(val api: IDataSource) {
    fun getUser(username: String) = api.getUser(username).subscribeOn(Schedulers.io())
}