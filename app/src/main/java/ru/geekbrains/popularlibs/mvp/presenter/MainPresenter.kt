package ru.geekbrains.popularlibs.mvp.presenter

import ru.geekbrains.popularlibs.mvp.model.CountersModel
import ru.geekbrains.popularlibs.mvp.view.MainView

class MainPresenter(val view: MainView, val model: CountersModel){

    fun firstCounterClick(){
        val nextValue = model.next(0)
        view.setFistButtonText(nextValue.toString())
    }

    fun secondCounterClick(){
        val nextValue = model.next(1)
        view.setSecondButtonText(nextValue.toString())
    }

    fun thirdCounterClick(){
        val nextValue = model.next(2)
        view.setThirdButtonText(nextValue.toString())
    }
}