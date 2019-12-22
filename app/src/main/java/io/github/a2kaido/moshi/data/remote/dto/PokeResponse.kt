package io.github.a2kaido.moshi.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeResponse(
    val name: String,
    val types: List<TypeWrapper>,
    val huga: String
)

@JsonClass(generateAdapter = true)
data class TypeWrapper(
    val slot: Int,
    val type: Type
)

@JsonClass(generateAdapter = true)
data class Type(
    val name: String
)
