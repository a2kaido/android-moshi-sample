package io.github.a2kaido.moshi.data

import io.github.a2kaido.moshi.data.remote.PokeService
import io.github.a2kaido.moshi.ui.repository.PokeRepository
import io.github.a2kaido.moshi.ui.domainmodel.Poke

class PokeRepositoryImpl(
    private val service: PokeService
) : PokeRepository {

    override suspend fun getPoke(name: String): Poke {
        return service.getPoke(name).let {
            Poke(
                it.name,
                it.types.map { typeWrapper ->
                    typeWrapper.type.name
                }.toList()
            )
        }
    }
}
