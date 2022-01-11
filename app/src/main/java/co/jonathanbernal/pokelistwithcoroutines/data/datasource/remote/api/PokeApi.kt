package co.jonathanbernal.pokelistwithcoroutines.data.datasource.remote.api

import co.jonathanbernal.pokelistwithcoroutines.data.datasource.remote.service.PokeListService
import co.jonathanbernal.pokelistwithcoroutines.model.PokemonResponse
import javax.inject.Inject

class PokeApi @Inject constructor(
    private val pokeListService: PokeListService
){

    suspend fun getPokeListFromApi() : PokemonResponse{
        return  pokeListService.getPokeList()
    }

}