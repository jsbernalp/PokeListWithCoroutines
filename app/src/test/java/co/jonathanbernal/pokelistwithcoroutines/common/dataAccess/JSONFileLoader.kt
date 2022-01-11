package co.jonathanbernal.pokelistwithcoroutines.common.dataAccess

import co.jonathanbernal.pokelistwithcoroutines.model.PokemonResponse
import com.google.gson.Gson
import java.io.InputStreamReader

class JSONFileLoader {

    private var jsonStr: String? = null

    fun loadJSONString(file:String):String?{
        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
        jsonStr = loader.readText()
        loader.close()
        return jsonStr
    }

    fun loadPokemonResponseEntity(file: String): PokemonResponse?{
        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
        jsonStr = loader.readText()
        loader.close()
        return Gson().fromJson(jsonStr,PokemonResponse::class.java)

    }

}