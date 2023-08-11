package com.example.pokemondictionary.data.repository

import com.example.pokemondictionary.data.network.PokemonApi
import com.example.pokemondictionary.data.network.PokemonDetailResponse
import javax.inject.Inject

interface PokemonDetailRepository {
    suspend fun getPokemonDetailInfo(number: Int): PokemonDetailResponse

}

class PokemonDetailRepositoryImpl @Inject constructor() : PokemonDetailRepository {
    override suspend fun getPokemonDetailInfo(number: Int): PokemonDetailResponse {
        return PokemonApi.retrofitService.getPokemonDetail(number)
    }

}