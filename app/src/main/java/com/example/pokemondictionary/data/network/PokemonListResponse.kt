package com.example.pokemondictionary.data.network

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)

@Serializable
data class Pokemon(
    val name: String,
    val url: String
)