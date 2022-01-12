package co.jonathanbernal.pokelistwithcoroutines.domain.usecase

import co.jonathanbernal.pokelistwithcoroutines.common.PokeEvent
import co.jonathanbernal.pokelistwithcoroutines.common.PokeException
import co.jonathanbernal.pokelistwithcoroutines.common.Response
import co.jonathanbernal.pokelistwithcoroutines.domain.interfaces.IPokeRepository
import co.jonathanbernal.pokelistwithcoroutines.model.Pokemon
import com.nhaarman.mockitokotlin2.given
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception


@RunWith(MockitoJUnitRunner::class)
class GetPokemonUseCaseTest {

    @Mock
    lateinit var iPokeRepository: IPokeRepository

    lateinit var getPokemonUseCase: GetPokemonUseCase

    @Before
    fun setup() {
        getPokemonUseCase = GetPokemonUseCase(iPokeRepository)
    }

    @Test
    fun `getPokeList received poke list from repository and return Response Object Success`() {
        runBlocking {
            val listPokemon =
                listOf(Pokemon("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/", null))
            `when`(iPokeRepository.getPokeList()).thenReturn(listPokemon)
            val result = getPokemonUseCase.getPokeListFromApi()
            assertEquals(Response.Success(listPokemon), result)
        }
    }

    @Test
    fun `getPokeList received POKEEXCEPTION(EMPTY_LIST) from repository and return Response Object Failure with this exception`() {
        runBlocking {
            val responseException = PokeException(PokeEvent.EMPTY_LIST)
            try {
                given(iPokeRepository.getPokeList()).willAnswer {
                    throw responseException
                }
                val result = getPokemonUseCase.getPokeListFromApi()
                assertEquals(Response.Failure(responseException), result)
            } catch (e: Exception) {
                (e as? PokeException).let {
                    assertThat(responseException, `is`(it!!.pokeEvent))
                }
            }
        }
    }
}