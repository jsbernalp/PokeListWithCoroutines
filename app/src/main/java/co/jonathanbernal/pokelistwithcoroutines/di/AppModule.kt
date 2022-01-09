package co.jonathanbernal.pokelistwithcoroutines.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideContext(application: PokeListApplication): Context = application.applicationContext

    @Singleton
    @Provides
    @JvmStatic
    fun provideApplication(application: PokeListApplication): Application = application



}