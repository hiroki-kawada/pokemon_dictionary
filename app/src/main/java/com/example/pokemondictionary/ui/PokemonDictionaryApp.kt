package com.example.pokemondictionary.ui


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemondictionary.ui.screens.list.PokemonListScreen
import com.example.pokemondictionary.ui.screens.list.PokemonListViewModel

/**
 * 一覧、詳細画面切り替え箇所
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDictionaryApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        val pokemonListViewModel: PokemonListViewModel = hiltViewModel()
                        PokemonListScreen(
                            pokemonListViewModel = pokemonListViewModel,
                            navController = navController,
                            modifier = modifier
                        )

                    }
                    composable("detail") {

                    }

                }

            }
        }
    )

}