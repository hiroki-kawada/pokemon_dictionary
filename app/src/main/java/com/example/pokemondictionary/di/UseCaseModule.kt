package com.example.pokemondictionary.di

import com.example.pokemondictionary.domain.PokemonDetailUseCase
import com.example.pokemondictionary.domain.PokemonDetailUseCaseImpl
import com.example.pokemondictionary.domain.PokemonListUseCase
import com.example.pokemondictionary.domain.PokemonListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun providePokemonListUseCase(
        pokemonListUseCase: PokemonListUseCaseImpl
    ): PokemonListUseCase {
        return pokemonListUseCase
    }

    @Provides
    fun providePokemonDetailUseCase(
        pokemonDetailUseCase: PokemonDetailUseCaseImpl
    ): PokemonDetailUseCase {
        return pokemonDetailUseCase
    }

}