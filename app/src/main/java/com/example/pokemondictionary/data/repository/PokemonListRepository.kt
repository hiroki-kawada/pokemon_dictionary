package com.example.pokemondictionary.data.repository

import com.example.pokemondictionary.data.network.PokemonApi
import com.example.pokemondictionary.data.network.PokemonListResponse
import javax.inject.Inject

/**
 * ポケモン一覧Repository
 */
interface PokemonListRepository {
    suspend fun getPokemonList(offset: Int, limit: Int): PokemonListResponse
}

class PokemonListRepositoryImpl @Inject constructor() : PokemonListRepository {

    /**
     * APIからポケモン一覧情報取得
     *
     * @param offset 取得開始図鑑No
     * @param limit 取得終了図鑑No
     * @return ポケモン一覧情報
     */
    override suspend fun getPokemonList(offset: Int, limit: Int): PokemonListResponse {
        return PokemonApi.retrofitService.getPokemonList(offset, limit)
    }
}