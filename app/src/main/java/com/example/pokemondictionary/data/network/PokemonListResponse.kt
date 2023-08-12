package com.example.pokemondictionary.data.network

import kotlinx.serialization.Serializable

/**
 * ポケモン一覧レスポンスデータ
 */
@Serializable
data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)

/**
 * ポケモン一覧アイテムレスポンスデータ
 */
@Serializable
data class Pokemon(
    val name: String,
    val url: String
)