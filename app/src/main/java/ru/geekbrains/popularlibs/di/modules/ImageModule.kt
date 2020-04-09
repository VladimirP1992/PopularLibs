package ru.geekbrains.popularlibs.di.modules


import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.geekbrains.popularlibs.mvp.model.cache.image.IImageCache
import ru.geekbrains.popularlibs.mvp.model.cache.image.room.RoomImageCache
import ru.geekbrains.popularlibs.mvp.model.entity.room.db.Database
import ru.geekbrains.popularlibs.mvp.model.image.IImageLoader
import ru.geekbrains.popularlibs.mvp.model.network.NetworkStatus
import ru.geekbrains.popularlibs.ui.App
import ru.geekbrains.popularlibs.ui.image.GlideImageLoader
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImageModule {

    //Не понял как тут возвращаемый тип правильно указать, т.к. интерфейс генерик
    @Provides
    fun imageLoader(imageCache: IImageCache, networkStatus: NetworkStatus): IImageLoader<ImageView>{
        return GlideImageLoader(imageCache, networkStatus)
    }

    @Singleton
    @Named("imageDir")
    @Provides
    fun dir(app: App): File{
        val path = (
                app.externalCacheDir?.path
                    ?: app.getExternalFilesDir(null)?.path
                    ?: app.filesDir.path
                ) + File.separator + "image_cache"
        return File(path)
    }

    @Singleton
    @Provides
    fun imageCache(database: Database, @Named("imageDir") dir: File): IImageCache{
        return RoomImageCache(database, dir)
    }
}
