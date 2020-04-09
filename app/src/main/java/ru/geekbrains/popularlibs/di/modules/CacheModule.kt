package ru.geekbrains.popularlibs.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibs.mvp.model.cache.IGithubRepositoriesCache
import ru.geekbrains.popularlibs.mvp.model.cache.IGithubUsersCache
import ru.geekbrains.popularlibs.mvp.model.cache.room.RoomGithubRepositoriesCache
import ru.geekbrains.popularlibs.mvp.model.cache.room.RoomGithubUsersCache
import ru.geekbrains.popularlibs.mvp.model.entity.room.MIGRATION_1_2
import ru.geekbrains.popularlibs.mvp.model.entity.room.MIGRATION_2_3
import ru.geekbrains.popularlibs.mvp.model.entity.room.db.Database
import ru.geekbrains.popularlibs.ui.App
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database {
        return Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
            .addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .build()
    }

    @Provides
    fun usersCache(database: Database): IGithubUsersCache {
        return RoomGithubUsersCache(database)
    }

    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(database)
    }

}