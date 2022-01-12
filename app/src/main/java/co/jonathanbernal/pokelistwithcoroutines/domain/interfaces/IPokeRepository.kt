package co.jonathanbernal.pokelistwithcoroutines.domain.interfaces

import co.jonathanbernal.pokelistwithcoroutines.model.Pokemon

interface IPokeRepository {

   suspend fun getPokeList(): List<Pokemon>
}