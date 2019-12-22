package io.github.a2kaido.moshi.ui.repository

import io.github.a2kaido.moshi.ui.domainmodel.Poke

interface PokeRepository {
    suspend fun getPoke(name: String): Poke
}