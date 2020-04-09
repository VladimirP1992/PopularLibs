package ru.geekbrains.popularlibs.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_repository.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.geekbrains.popularlibs.R
import ru.geekbrains.popularlibs.mvp.model.entity.GithubRepository
import ru.geekbrains.popularlibs.mvp.presenter.RepositoryPresenter
import ru.geekbrains.popularlibs.mvp.view.RepositoryView
import ru.geekbrains.popularlibs.ui.App
import ru.geekbrains.popularlibs.ui.BackButtonListener

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    companion object {
        const val REPOSITORY_KEY = "repository"

        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_KEY, repository)
            }
        }
    }

    @InjectPresenter
    lateinit var presenter: RepositoryPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_repository, null)

    @ProvidePresenter
    fun providePresenter() = RepositoryPresenter(arguments!![REPOSITORY_KEY] as GithubRepository).apply {
        App.instance.appComponent.inject(this)
    }

    override fun init() {

    }

    override fun setId(text: String) {
        rep_id_value.text = text
    }

    override fun setTitle(text: String) {
        rep_name_value.text = text
    }

    override fun setForksCount(text: String) {
        rep_forks_count_value.text = text
    }

    override fun backClicked() = presenter.backClicked()
}