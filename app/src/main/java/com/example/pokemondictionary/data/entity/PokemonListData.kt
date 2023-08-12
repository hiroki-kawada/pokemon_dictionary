package com.example.pokemondictionary.data.entity


/**
 * ポケモン一覧データ
 */
data class PokemonListData(
    val next: String?, //一覧画面の次ページURL
    val previous: String?, //一覧画面の次ページURL
    val listData: List<ListItemData> //一覧表示データ
)
