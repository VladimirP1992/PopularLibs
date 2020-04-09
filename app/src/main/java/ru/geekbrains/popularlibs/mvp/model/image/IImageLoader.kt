package ru.geekbrains.popularlibs.mvp.model.image

import ru.geekbrains.popularlibs.mvp.model.cache.image.IImageCache

interface IImageLoader<T> {
    val cache: IImageCache
    fun loadInto(url: String, container: T)
}