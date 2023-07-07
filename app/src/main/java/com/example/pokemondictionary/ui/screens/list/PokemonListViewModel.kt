package com.example.pokemondictionary.ui.screens.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemondictionary.data.entity.PokemonListData
import com.example.pokemondictionary.domain.PokemonListUseCase
import com.example.pokemondictionary.domain.PokemonListUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


interface ListUiState {
    data class Success(val pokemonListData: PokemonListData) : ListUiState
    object Error : ListUiState
    object Loading : ListUiState
}

/**
 * 一覧画面ViewModel
 */
@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val mPokemonListUseCase: PokemonListUseCase
) : ViewModel() {
    var listUiState: ListUiState by mutableStateOf(ListUiState.Loading)
        private set

    init {
        getPokemonList(0, 20)
    }

    fun getPokemonList(offset: Int, limit: Int) {
        listUiState = ListUiState.Loading
        viewModelScope.launch {
            listUiState = try {
                val detailResult = mPokemonListUseCase.getListItemProfileDataList(offset, limit)
                ListUiState.Success(detailResult)
            } catch (e: IOException) {
                ListUiState.Error
            }
        }
    }
}