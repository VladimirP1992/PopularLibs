package ru.geekbrains.popularlibs.ui

import android.app.Application
import ru.geekbrains.popularlibs.mvp.model.entity.room.db.Database
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import timber.log.Timber

//Пока не знаем Dagger
class App : Application() {
    companion object {
        lateinit var instance: App
    }

    val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
        Database.create(this)
    }

    val navigatorHolder get() = cicerone.navigatorHolder
    val router get() = cicerone.router
}