package ru.geekbrains.popularlibs.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibs.mvp.model.api.IDataSource
import ru.geekbrains.popularlibs.mvp.model.cache.IGithubRepositoriesCache
import ru.geekbrains.popularlibs.mvp.model.cache.IGithubUsersCache
import ru.geekbrains.popularlibs.mvp.model.network.NetworkStatus
import ru.geekbrains.popularlibs.mvp.model.repo.GithubRepositoriesRepo
import ru.geekbrains.popularlibs.mvp.model.repo.GithubUsersRepo
import javax.inject.Singleton

@Module(
    includes = [
        CacheModule::class,
        ApiModule::class
    ]
)
open class RepoModule {

    @Singleton
    @Provides
    open fun usersRepo(api: IDataSource, networkStatus: NetworkStatus, cache: IGithubUsersCache): GithubUsersRepo {
        return GithubUsersRepo(api, networkStatus, cache)
    }

    @Singleton
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: NetworkStatus, cache: IGithubRepositoriesCache): GithubRepositoriesRepo {
        return GithubRepositoriesRepo(api, networkStatus, cache)
    }
}