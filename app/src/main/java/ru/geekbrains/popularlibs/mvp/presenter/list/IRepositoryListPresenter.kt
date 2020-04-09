package ru.geekbrains.popularlibs.mvp.presenter.list

import ru.geekbrains.popularlibs.mvp.view.list.RepositoryItemView

interface IRepositoryListPresenter {
    var itemClickListener: ((RepositoryItemView) -> Unit)?
    fun getCount(): Int
    fun bindView(view: RepositoryItemView)
}