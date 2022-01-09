package co.jonathanbernal.pokelistwithcoroutines.data.datasource.remote.api

import co.jonathanbernal.pokelistwithcoroutines.data.datasource.remote.service.PokeListService
import javax.inject.Inject

class PokeApi @Inject constructor(
    private val pokeListService: PokeListService
){

}