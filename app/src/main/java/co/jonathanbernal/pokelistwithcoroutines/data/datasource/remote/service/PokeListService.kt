package co.jonathanbernal.pokelistwithcoroutines.data.datasource.remote.service

import co.jonathanbernal.pokelistwithcoroutines.model.PokemonResponse
import retrofit2.http.GET

interface PokeListService {

    @GET("pokemon")
    suspend fun getPokeList(): PokemonResponse

}