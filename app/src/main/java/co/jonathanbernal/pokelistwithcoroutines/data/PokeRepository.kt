package co.jonathanbernal.pokelistwithcoroutines.data

import co.jonathanbernal.pokelistwithcoroutines.data.datasource.remote.api.PokeApi
import co.jonathanbernal.pokelistwithcoroutines.domain.IPokeRepository
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val pokeApi: PokeApi
): IPokeRepository {

}