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
        private const val ARGS_REPOSITORY  = "argsRepository"
        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGS_REPOSITORY, repository)
            }
        }
    }

    @InjectPresenter
    lateinit var presenter: RepositoryPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_repository, null)

    @ProvidePresenter
    fun providePresenter():RepositoryPresenter{
        val repository = arguments!![ARGS_REPOSITORY] as GithubRepository
        return RepositoryPresenter(repository, App.instance.getRouter())
    }

    override fun setRepoId(id: String) {
        rep_id.text = getString(R.string.id)
        rep_id_value.text = id
    }

    override fun setRepoName(name: String) {
        rep_name.text = getString(R.string.name)
        rep_name_value.text = name
    }

    override fun setRepoForksCount(forksCount: String) {
        rep_forks_count.text = getString(R.string.forks_count)
        rep_forks_count_value.text = forksCount
    }

    override fun backClicked(): Boolean = presenter.backClicked()
}