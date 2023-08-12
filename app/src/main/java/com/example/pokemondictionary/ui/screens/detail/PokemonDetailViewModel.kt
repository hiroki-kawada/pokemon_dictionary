package com.example.pokemondictionary.ui.screens.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemondictionary.data.entity.PokemonDetailData
import com.example.pokemondictionary.domain.PokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


/**
 * 詳細情報画面スタータス
 */
interface DetailUiState {
    data class Success(val pokemonDetailData: PokemonDetailData) : DetailUiState
    object Error : DetailUiState
    object Loading : DetailUiState
}

/**
 * 詳細画面ViewModel
 */
@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val mPokemonListUseCase: PokemonDetailUseCase
) : ViewModel() {
    var detailUiState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    /**
     * 詳細情報取得
     *
     * @param number 図鑑No
     */
    fun getPokemonDetailInfo(number: Int) {
        detailUiState = DetailUiState.Loading
        viewModelScope.launch {
            detailUiState = try {
                val pokemonDetailData = mPokemonListUseCase.getPokemonDetailData(number)
                DetailUiState.Success(pokemonDetailData)
            } catch (e: IOException) {
                DetailUiState.Error
            }
        }
    }
}