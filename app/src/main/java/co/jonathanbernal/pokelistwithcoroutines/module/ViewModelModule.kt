package co.jonathanbernal.pokelistwithcoroutines.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.jonathanbernal.pokelistwithcoroutines.common.ViewModelFactory
import co.jonathanbernal.pokelistwithcoroutines.di.ViewModelKey
import co.jonathanbernal.pokelistwithcoroutines.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun searchViewModel(viewModelRecent: MainViewModel): ViewModel

}