package ru.geekbrains.popularlibs.ui.activity

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.geekbrains.popularlibs.R
import ru.geekbrains.popularlibs.mvp.presenter.MainPresenter
import ru.geekbrains.popularlibs.mvp.view.MainView
import ru.geekbrains.popularlibs.ui.App
import ru.geekbrains.popularlibs.ui.BackButtonListener
import ru.geekbrains.popularlibs.ui.adapter.RepositoriesRVAdapter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), MainView {

    val navigator = SupportAppNavigator(this, R.id.container)

    @InjectPresenter
    lateinit var presenter: MainPresenter

    var adapter: RepositoriesRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @ProvidePresenter
    fun providePresenter() = MainPresenter(App.instance.getRouter())

    override fun init() {

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.getNavigatorHolder().removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backClicked()){
                return
            }
        }
        presenter.backClicked()
    }


}