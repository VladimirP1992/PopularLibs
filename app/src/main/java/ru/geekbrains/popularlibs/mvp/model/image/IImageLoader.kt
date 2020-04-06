package ru.geekbrains.popularlibs.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}