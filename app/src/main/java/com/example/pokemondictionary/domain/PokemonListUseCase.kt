package com.example.pokemondictionary.domain

import com.example.pokemondictionary.data.entity.ListItemData
import com.example.pokemondictionary.data.entity.PokemonListData
import com.example.pokemondictionary.data.repository.PokemonListRepository
import javax.inject.Inject

interface PokemonListUseCase {
    suspend fun getListItemProfileDataList(offset: Int, limit: Int): PokemonListData
}

class PokemonListUseCaseImpl @Inject constructor(
    private val mPokemonListRepository: PokemonListRepository
) : PokemonListUseCase {
    override suspend fun getListItemProfileDataList(offset: Int, limit: Int): PokemonListData {
        val list: MutableList<ListItemData> = mutableListOf()
        val listResult = mPokemonListRepository.getPokemonList(offset, limit)
        var number = offset
        for (i in listResult.results) {
            ++number
            list.add(
                ListItemData(
                    number = number.toString(),
                    name = i.name,
                    url = i.url
                )
            )
        }

        return PokemonListData(listResult.next, listResult.previous, list)
    }
}