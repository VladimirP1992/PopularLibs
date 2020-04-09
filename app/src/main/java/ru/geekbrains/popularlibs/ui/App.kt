package ru.geekbrains.popularlibs.ui

import android.app.Application
import ru.geekbrains.popularlibs.di.AppComponent
import ru.geekbrains.popularlibs.di.DaggerAppComponent
import ru.geekbrains.popularlibs.di.modules.AppModule
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}