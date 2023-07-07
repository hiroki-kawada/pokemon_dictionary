package com.example.pokemondictionary.data.entity

data class PokemonListData(
    val next: String?,
    val previous: String?,
    val listData: List<ListItemData>
)
