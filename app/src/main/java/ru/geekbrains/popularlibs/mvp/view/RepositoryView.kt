package ru.geekbrains.popularlibs.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoryView : MvpView {
    fun setRepoId(id : String)
    fun setRepoName(name : String)
    fun setRepoForksCount(forksCount : String)
}