package co.jonathanbernal.pokelistwithcoroutines.common.dataAccess

import co.jonathanbernal.pokelistwithcoroutines.model.PokemonResponse
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class ResponseServerTest {
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun `read JSON file success`(){
        val reader = JSONFileLoader().loadJSONString("poke_list_response_success")
        MatcherAssert.assertThat(reader, Matchers.`is`(Matchers.notNullValue()))
        MatcherAssert.assertThat(reader, Matchers.containsString("bulbasaur"))
    }

    @Test
    fun `get pokelist and check bulbasaur exist`(){
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(JSONFileLoader().loadJSONString("poke_list_response_success") ?: "{errorCode: 34}")
        mockWebServer.enqueue(response)

        MatcherAssert.assertThat(
            response.getBody()?.readUtf8(),
            Matchers.containsString("bulbasaur")
        )
    }




    @Test
    fun `get pokelist check contains results list no empty`(){
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(JSONFileLoader().loadJSONString("poke_list_response_success") ?: "{errorCode: 34}")
        mockWebServer.enqueue(response)
        MatcherAssert.assertThat(response.getBody()?.readUtf8(), Matchers.containsString("bulbasaur"))

        val json = Gson().fromJson(response.getBody()?.readUtf8() ?: "", PokemonResponse::class.java)
        MatcherAssert.assertThat(json.results.isEmpty(), Matchers.`is`(false))
    }

}