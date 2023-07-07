package com.example.pokemondictionary.data.repository

import com.example.pokemondictionary.data.network.PokemonApi
import com.example.pokemondictionary.data.network.PokemonListResponse
import javax.inject.Inject

interface PokemonListRepository {
    suspend fun getPokemonList(offset: Int, limit: Int): PokemonListResponse
}

class PokemonListRepositoryImpl @Inject constructor() : PokemonListRepository {
    override suspend fun getPokemonList(offset: Int, limit: Int): PokemonListResponse {
        return PokemonApi.retrofitService.getPokemonList(offset, limit)
    }
}