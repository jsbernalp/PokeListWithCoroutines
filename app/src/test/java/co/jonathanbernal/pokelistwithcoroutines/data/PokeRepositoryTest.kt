package co.jonathanbernal.pokelistwithcoroutines.data

import co.jonathanbernal.pokelistwithcoroutines.common.PokeEvent
import co.jonathanbernal.pokelistwithcoroutines.common.PokeException
import co.jonathanbernal.pokelistwithcoroutines.common.Response
import co.jonathanbernal.pokelistwithcoroutines.data.datasource.remote.api.PokeApi
import co.jonathanbernal.pokelistwithcoroutines.model.Pokemon
import co.jonathanbernal.pokelistwithcoroutines.model.PokemonResponse
import co.jonathanbernal.pokelistwithcoroutines.utils.CoroutinesRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class PokeRepositoryTest{

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesRule = CoroutinesRule()

    private lateinit var pokeRepository: PokeRepository

    @Mock
    private lateinit var pokeApi: PokeApi

    @Before
    fun setup(){
        pokeRepository = PokeRepository(pokeApi)
    }

    @Test
    fun `check poke list is not null`(){
        runBlocking {
            val listPokemon = listOf(Pokemon("bulbasaur","https://pokeapi.co/api/v2/pokemon/1/",null))
            val apiResponseExpected = PokemonResponse(listPokemon)
            `when`(pokeApi.getPokeListFromApi()).thenReturn(apiResponseExpected)
            val result = pokeRepository.getPokeList()
            assertThat(result,`is`(notNullValue()))
        }
    }

    @Test
    fun `check poke list is not empty`(){
        runBlocking {
            val listPokemon = listOf(Pokemon("bulbasaur","https://pokeapi.co/api/v2/pokemon/1/",null))
            val apiResponseExpected = PokemonResponse(listPokemon)
            `when`(pokeApi.getPokeListFromApi()).thenReturn(apiResponseExpected)
            val result = pokeRepository.getPokeList()
            assertThat(result.size ,`is`(1))
        }
    }

    @Test
    fun `check poke list is empty`() {
        runBlocking {
            try {
                val apiResponseExpected = PokemonResponse(emptyList())
                `when`(pokeApi.getPokeListFromApi()).thenReturn(apiResponseExpected)
                val result = pokeRepository.getPokeList()
                assertEquals(Response.Failure(PokeException(PokeEvent.EMPTY_LIST)),result)
            } catch (e: Exception) {
                (e as? PokeException).let {
                    println("Esta es la exception = ${it!!.pokeEvent}")
                    assertEquals(PokeEvent.EMPTY_LIST, it.pokeEvent)
                }
            }
        }
    }

}