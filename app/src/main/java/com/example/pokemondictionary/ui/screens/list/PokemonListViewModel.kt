package com.example.pokemondictionary.ui.screens.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemondictionary.data.entity.PokemonListData
import com.example.pokemondictionary.domain.PokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

/**
 * 一覧情報画面スタータス
 */
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

    /**
     * ポケモン一覧情報取得
     *
     * @param offset 取得開始図鑑No
     * @param limit 取得終了図鑑No
     */
    fun getPokemonList(offset: Int, limit: Int) {
        listUiState = ListUiState.Loading

        viewModelScope.launch {
            listUiState = try {
                val detailResult = mPokemonListUseCase.getListItemPokemonData(offset, limit)

                ListUiState.Success(detailResult)
            } catch (e: IOException) {
                ListUiState.Error
            }
        }
    }

    /**
     * 初期処理
     */
    init {
        getPokemonList(0, 20)
    }
}