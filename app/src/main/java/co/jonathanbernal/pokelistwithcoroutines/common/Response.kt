package co.jonathanbernal.pokelistwithcoroutines.common

import co.jonathanbernal.pokelistwithcoroutines.model.Pokemon

sealed class Response {
    data class Success(val data: List<Pokemon>) : Response()
    data class Failure(val throwable: Throwable) : Response()
}