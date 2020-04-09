package ru.geekbrains.popularlibs.di

import dagger.Component
import ru.geekbrains.popularlibs.di.modules.AppModule
import ru.geekbrains.popularlibs.di.modules.CiceroneModule
import ru.geekbrains.popularlibs.di.modules.ImageModule
import ru.geekbrains.popularlibs.di.modules.RepoModule
import ru.geekbrains.popularlibs.mvp.presenter.MainPresenter
import ru.geekbrains.popularlibs.mvp.presenter.RepositoriesPresenter
import ru.geekbrains.popularlibs.mvp.presenter.RepositoryPresenter
import ru.geekbrains.popularlibs.ui.activity.MainActivity
import ru.geekbrains.popularlibs.ui.fragments.RepositoriesFragment
import ru.geekbrains.popularlibs.ui.image.GlideImageLoader
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RepoModule::class,
        CiceroneModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(repositoriesPresenter: RepositoriesPresenter)
    fun inject(repositoriesFragment: RepositoriesFragment)
    fun inject(repositoryPresenter: RepositoryPresenter)
}