package co.jonathanbernal.pokelistwithcoroutines.common

import java.lang.Exception

class PokeException (val pokeEvent: PokeEvent, msg:String? = null): Exception(msg)