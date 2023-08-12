package com.example.pokemondictionary.data.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

/**
 * ポケモン処理情報レスポンスデータ
 */
@Serializable
data class PokemonDetailResponse(
    val abilities: List<Ability>,
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)

/**
 * 特性データ
 */
@Serializable
data class Ability(
    val ability: AbilityData,
    val is_hidden: Boolean,
    val slot: Int
)

/**
 * 特性詳細データ
 */
@Serializable
data class AbilityData(
    val name: String,
    val url: String
)

/**
 * 画像まとめデータ
 */
@Serializable
data class Sprites(
    val back_default: String?,
    val back_female: String?,
    val back_shiny: String?,
    val back_shiny_female: String?,
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?,
    val other: Other,
)

/**
 * 画像その他データ
 */
@Serializable
data class Other(
    val dream_world: DreamWorld,
    val home: Home,
    @JsonNames("official-artwork") val officialartwork: OfficialArtwork
)

/**
 * 画像その他詳細データ
 */
@Serializable
data class DreamWorld(
    val front_default: String?,
    val front_female: String?
)

/**
 * 画像基礎データ
 */
@Serializable
data class Home(
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)

/**
 * 画像公式データ
 */
@Serializable
data class OfficialArtwork(
    val front_default: String?,
    val front_shiny: String?
)

/**
 * ポケモンステータスデータ
 */
@Serializable
data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: StatX
)

/**
 * ポケモンステータス詳細データ
 */
@Serializable
data class StatX(
    val name: String,
    val url: String
)

/**
 * ポケモンタイプデータ
 */
@Serializable
data class Type(
    val slot: Int,
    val type: TypeX
)

/**
 * ポケモンタイプ詳細データ
 */
@Serializable
data class TypeX(
    val name: String,
    val url: String
)