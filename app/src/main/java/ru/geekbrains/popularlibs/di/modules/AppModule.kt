package ru.geekbrains.popularlibs.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibs.ui.App


@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}