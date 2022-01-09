package co.jonathanbernal.pokelistwithcoroutines.di

import android.app.Application
import android.content.Context
import co.jonathanbernal.pokelistwithcoroutines.module.NetworkModule
import co.jonathanbernal.pokelistwithcoroutines.module.RepositoryModule
import co.jonathanbernal.pokelistwithcoroutines.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ActivitiesBuilder::class]
)
@Singleton
interface AppComponent : AndroidInjector<PokeListApplication> {

    fun context(): Context
    fun application(): Application

    @Component.Builder
    interface Builder{

        fun build():AppComponent

        @BindsInstance
        fun application(application: PokeListApplication):Builder
    }
}