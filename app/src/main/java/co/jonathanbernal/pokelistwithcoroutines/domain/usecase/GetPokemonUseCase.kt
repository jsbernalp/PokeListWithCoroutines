package co.jonathanbernal.pokelistwithcoroutines.domain.usecase

import co.jonathanbernal.pokelistwithcoroutines.common.PokeEvent
import co.jonathanbernal.pokelistwithcoroutines.common.PokeException
import co.jonathanbernal.pokelistwithcoroutines.common.Response
import co.jonathanbernal.pokelistwithcoroutines.domain.interfaces.IPokeRepository
import java.lang.Exception
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val iPokeRepository: IPokeRepository
) {

    suspend fun getPokeListFromApi(): Response{
        return try {
            Response.Success(iPokeRepository.getPokeList())
        }catch (e:Exception){
            Response.Failure(e)
        }
    }
}