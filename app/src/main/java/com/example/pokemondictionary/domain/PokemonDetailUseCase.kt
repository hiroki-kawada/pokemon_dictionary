package com.example.pokemondictionary.domain

import com.example.pokemondictionary.data.entity.AbilityData
import com.example.pokemondictionary.data.entity.PokemonDetailData
import com.example.pokemondictionary.data.entity.TypeData
import com.example.pokemondictionary.data.repository.PokemonDetailRepository
import javax.inject.Inject

/**
 * ポケモン詳細UseCase
 */
interface PokemonDetailUseCase {
    suspend fun getPokemonDetailData(number: Int): PokemonDetailData
}

class PokemonDetailUseCaseImpl @Inject constructor(
    private val mPokemonDetailRepository: PokemonDetailRepository
) : PokemonDetailUseCase {

    /**
     * ポケモン詳細情報取得
     *
     * @param number ポケモン図鑑No
     * @return ViewModel用に整形したポケモン詳細情報
     */
    override suspend fun getPokemonDetailData(number: Int): PokemonDetailData {
        val detailResult = mPokemonDetailRepository.getPokemonDetailInfo(number)
        val abilityList: MutableList<AbilityData> = mutableListOf()
        val typeList: MutableList<TypeData> = mutableListOf()
        for (i in detailResult.abilities) {
            abilityList.add(
                AbilityData(
                    name = i.ability.name,
                    hiddenAbility = i.is_hidden
                )
            )
        }
        for (i in detailResult.types) {
            typeList.add(
                TypeData(
                    name = i.type.name
                )
            )
        }
        return PokemonDetailData(
            name = detailResult.name,
            no = detailResult.id,
            imageUrl = detailResult.sprites.other.officialartwork.front_default,
            height = detailResult.height,
            weight = detailResult.weight,
            abilities = abilityList,
            types = typeList
        )
    }
}