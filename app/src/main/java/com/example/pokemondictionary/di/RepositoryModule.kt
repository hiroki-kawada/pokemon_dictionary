package com.example.pokemondictionary.di

import com.example.pokemondictionary.data.repository.PokemonListRepository
import com.example.pokemondictionary.data.repository.PokemonListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun providePokemonListRepository(
        pokemonListRepository: PokemonListRepositoryImpl
    ): PokemonListRepository {
        return pokemonListRepository
    }
}