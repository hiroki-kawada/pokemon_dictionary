package com.example.pokemondictionary.ui.screens.list

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pokemondictionary.data.entity.ListItemData
import com.example.pokemondictionary.data.entity.PokemonListData

/**
 * 一覧画面
 */
@Composable
fun PokemonListScreen(
    pokemonListViewModel: PokemonListViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    when (pokemonListViewModel.listUiState) {
        is ListUiState.Loading -> LoadingScreen(modifier)
        is ListUiState.Success -> ResultScreen(
            (pokemonListViewModel.listUiState as ListUiState.Success).pokemonListData,
            modifier,
            { offset, limit -> pokemonListViewModel.getPokemonList(offset, limit) },
            navController

        )

        is ListUiState.Error -> ErrorScreen(modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun ResultScreen(
    pokemonListData: PokemonListData,
    modifier: Modifier = Modifier, pokemonListViewModel: (Int, Int) -> Unit,
    navController: NavController
) {

    val previousButtonEnable = pokemonListData.previous != null
    val nextButtonEnable = pokemonListData.next != null

    LazyColumn {
        items(pokemonListData.listData) { item ->
            ListItem(
                item, navController,
                Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .height(50.dp)
            )
        }
        item {
            //画面更新のボタン表示
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Button(
                    onClick = {
                        pokemonListData.previous?.let {
                            val list = getListData(it)
                            pokemonListViewModel(list[0], list[1])
                        }
                    },
                    enabled = previousButtonEnable,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Text(text = "<")
                }
                Button(
                    onClick = {
                        pokemonListData.next?.let {
                            val list = getListData(it)
                            pokemonListViewModel(list[0], list[1])
                        }
                    }, enabled = nextButtonEnable,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Text(text = ">")
                }
            }
        }
    }
}

@Composable
fun ListItem(
    listItemData: ListItemData, navController: NavController, modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
            .clickable { navController.navigate("detail") }
    ) {
        Text(
            text = listItemData.number,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = listItemData.name,
            fontSize = 18.sp
        )
    }

}


@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "エラー画面")
    }
}

/**
 * リスト情報取得
 * @param url 取得時に必要な情報のURL
 */
fun getListData(url: String): List<Int> {
    val mutableList = mutableListOf<Int>()
    val uri = Uri.parse(url)
    val offset = uri.getQueryParameter("offset")?.toInt()
    val limit = uri.getQueryParameter("limit")?.toInt()
    if (offset != null && limit != null) {
        mutableList.add(offset)
        mutableList.add(limit)
    }
    return mutableList
}
