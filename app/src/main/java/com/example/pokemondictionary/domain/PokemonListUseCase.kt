package com.example.pokemondictionary.domain

import com.example.pokemondictionary.data.entity.ListItemData
import com.example.pokemondictionary.data.entity.PokemonListData
import com.example.pokemondictionary.data.repository.PokemonListRepository
import javax.inject.Inject

/**
 * ポケモン一覧UseCase
 **/
interface PokemonListUseCase {
    suspend fun getListItemPokemonData(offset: Int, limit: Int): PokemonListData
}

class PokemonListUseCaseImpl @Inject constructor(
    private val mPokemonListRepository: PokemonListRepository
) : PokemonListUseCase {

    /**
     * APIからポケモン一覧情報取得
     *
     * @param offset 取得開始図鑑No
     * @param limit 取得終了図鑑No
     * @return ViewModel用に整形したポケモン一覧情報
     */
    override suspend fun getListItemPokemonData(offset: Int, limit: Int): PokemonListData {
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