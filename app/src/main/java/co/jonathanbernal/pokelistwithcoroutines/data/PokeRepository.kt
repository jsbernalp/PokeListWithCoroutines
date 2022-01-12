package co.jonathanbernal.pokelistwithcoroutines.data

import co.jonathanbernal.pokelistwithcoroutines.common.PokeEvent
import co.jonathanbernal.pokelistwithcoroutines.common.PokeException
import co.jonathanbernal.pokelistwithcoroutines.data.datasource.remote.api.PokeApi
import co.jonathanbernal.pokelistwithcoroutines.domain.interfaces.IPokeRepository
import co.jonathanbernal.pokelistwithcoroutines.model.Pokemon
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val pokeApi: PokeApi
): IPokeRepository {

    override suspend fun getPokeList(): List<Pokemon> {
        return if (pokeApi.getPokeListFromApi().results.isNotEmpty()){
            pokeApi.getPokeListFromApi().results
        }else{
            throw PokeException(PokeEvent.EMPTY_LIST,"el listado de pokemones esta vacio")
        }
    }
}