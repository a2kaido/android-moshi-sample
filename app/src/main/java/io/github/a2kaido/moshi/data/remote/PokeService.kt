package io.github.a2kaido.moshi.data.remote

import io.github.a2kaido.moshi.data.remote.dto.PokeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeService {

    @GET("pokemon/{name}/")
    suspend fun getPoke(@Path("name") name: String): PokeResponse
}