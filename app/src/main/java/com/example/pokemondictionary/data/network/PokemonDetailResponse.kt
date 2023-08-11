package com.example.pokemondictionary.data.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

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

@Serializable
data class Ability(
    val ability: AbilityData,
    val is_hidden: Boolean,
    val slot: Int
)

@Serializable
data class AbilityData(
    val name: String,
    val url: String
)

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

@Serializable
data class Other(
    val dream_world: DreamWorld,
    val home: Home,
    @JsonNames("official-artwork")val officialartwork: OfficialArtwork
)

@Serializable
data class DreamWorld(
    val front_default: String?,
    val front_female: String?
)

@Serializable
data class Home(
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)

@Serializable
data class OfficialArtwork(
    val front_default: String?,
    val front_shiny: String?
)

@Serializable
data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: StatX
)

@Serializable
data class StatX(
    val name: String,
    val url: String
)

@Serializable
data class Type(
    val slot: Int,
    val type: TypeX
)

@Serializable
data class TypeX(
    val name: String,
    val url: String
)