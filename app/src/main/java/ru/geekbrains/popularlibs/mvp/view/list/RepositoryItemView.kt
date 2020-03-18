package ru.geekbrains.popularlibs.mvp.view.list

interface RepositoryItemView {
    var pos: Int

    fun setTitle(text: String)
}