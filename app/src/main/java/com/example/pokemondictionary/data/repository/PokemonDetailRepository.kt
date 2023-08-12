package com.example.pokemondictionary.data.repository

import com.example.pokemondictionary.data.network.PokemonApi
import com.example.pokemondictionary.data.network.PokemonDetailResponse
import javax.inject.Inject

/**
 * ポケモン詳細Repository
 */
interface PokemonDetailRepository {
    suspend fun getPokemonDetailInfo(number: Int): PokemonDetailResponse

}

class PokemonDetailRepositoryImpl @Inject constructor() : PokemonDetailRepository {
    /**
     * APIからポケモン詳細情報取得
     *
     * @param number ポケモン図鑑No
     * @return ポケモン詳細情報
     */
    override suspend fun getPokemonDetailInfo(number: Int): PokemonDetailResponse {
        return PokemonApi.retrofitService.getPokemonDetail(number)
    }

}