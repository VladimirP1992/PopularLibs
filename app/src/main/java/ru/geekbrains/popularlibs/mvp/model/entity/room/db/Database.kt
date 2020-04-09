package ru.geekbrains.popularlibs.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import ru.geekbrains.popularlibs.mvp.model.entity.room.RoomCachedImage
import ru.geekbrains.popularlibs.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.popularlibs.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.popularlibs.mvp.model.entity.room.dao.ImageDao
import ru.geekbrains.popularlibs.mvp.model.entity.room.dao.RepositoryDao
import ru.geekbrains.popularlibs.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepository::class,
        RoomCachedImage::class
    ],
    version = 3
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: ImageDao

    companion object {
        const val DB_NAME = "database.db"
    }
}