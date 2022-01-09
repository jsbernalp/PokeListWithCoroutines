package co.jonathanbernal.pokelistwithcoroutines.di

import co.jonathanbernal.pokelistwithcoroutines.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuilder {

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity

}