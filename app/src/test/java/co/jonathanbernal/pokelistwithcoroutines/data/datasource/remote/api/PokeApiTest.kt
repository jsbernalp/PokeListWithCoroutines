package co.jonathanbernal.pokelistwithcoroutines.data.datasource.remote.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.jonathanbernal.pokelistwithcoroutines.common.dataAccess.JSONFileLoader
import co.jonathanbernal.pokelistwithcoroutines.data.datasource.remote.service.PokeListService
import co.jonathanbernal.pokelistwithcoroutines.utils.CoroutinesRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeApiTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesRule = CoroutinesRule()

    private lateinit var pokeApi: PokeApi
    private lateinit var pokeListService: PokeListService

    companion object {
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            retrofit = Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @Before
    fun setup(){
        pokeListService = retrofit.create(PokeListService::class.java)
        pokeApi = PokeApi(pokeListService)
    }


    @Test
    fun checkPokeListIsNotNullTest() {
        runBlocking {
            val result = pokeApi.getPokeListFromApi()
            println("resultados de la consulta ${result.results}")
            assertThat(result.results, `is`(notNullValue()))
        }
    }

    @Test
    fun checkPokeListSizeTest(){
        val listSizeExpected = 20
        runBlocking {
            val result = pokeApi.getPokeListFromApi()
            println("resultados de la consulta ${result.results.size}")
            assertThat(result.results.size, `is`(listSizeExpected))
        }
    }

    @Test
    fun checkPokeListSizeRemoteWithLocalTest(){
        runBlocking {
            val remoteResult = pokeApi.getPokeListFromApi()
            val localResult = JSONFileLoader().loadPokemonResponseEntity("poke_list_response_success")

            assertThat(localResult?.results?.size, CoreMatchers.`is`(remoteResult.results.size))
        }
    }
}