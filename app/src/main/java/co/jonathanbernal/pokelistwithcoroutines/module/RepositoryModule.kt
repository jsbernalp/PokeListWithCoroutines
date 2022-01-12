package co.jonathanbernal.pokelistwithcoroutines.module

import co.jonathanbernal.pokelistwithcoroutines.data.PokeRepository
import co.jonathanbernal.pokelistwithcoroutines.data.datasource.remote.api.PokeApi
import co.jonathanbernal.pokelistwithcoroutines.domain.interfaces.IPokeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providePokeRepository(pokeApi: PokeApi): IPokeRepository {
        return PokeRepository(pokeApi)
    }

}