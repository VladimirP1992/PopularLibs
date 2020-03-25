package ru.geekbrains.popularlibs.ui

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

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
    }

    fun getNavigatorHolder() = cicerone.navigatorHolder
    fun getRouter() = cicerone.router
}