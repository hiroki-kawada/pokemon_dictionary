package com.example.pokemondictionary.data.entity


data class PokemonDetailData(
    val name: String,//名前
    val no: Int,//ナンバー
    val imageUrl: String?,//画像URL
    val height: Int,//高さ
    val weight: Int,//重さ
    val abilities: List<AbilityData>,//特性
    val types: List<TypeData>,//タイプ

)

data class AbilityData(
    val name: String,
    val hiddenAbility: Boolean,
)

data class TypeData(
    val name: String,
)