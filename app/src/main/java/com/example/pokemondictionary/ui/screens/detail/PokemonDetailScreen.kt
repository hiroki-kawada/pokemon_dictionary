package com.example.pokemondictionary.ui.screens.detail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pokemondictionary.data.entity.PokemonDetailData
import com.example.pokemondictionary.data.entity.PokemonListData
import com.example.pokemondictionary.data.entity.TypeData

/**
 * 詳細画面
 */
@Composable
fun PokemonDetailScreen(
    pokemonDetailViewModel: PokemonDetailViewModel,
    number: String,
    modifier: Modifier = Modifier
) {
    when (pokemonDetailViewModel.detailUiState) {
        is DetailUiState.Loading -> LoadingScreen(
            number.toInt(),
            pokemonDetailViewModel
        )

        is DetailUiState.Success -> ResultScreen(
            pokemonDetailData = (pokemonDetailViewModel.detailUiState as DetailUiState.Success).pokemonDetailData
        )

        is DetailUiState.Error -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen(
    number: Int,
    pokemonDetailViewModel: PokemonDetailViewModel,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
    pokemonDetailViewModel.getPokemonDetailInfo(number)
}

@Composable
fun ResultScreen(
    pokemonDetailData: PokemonDetailData,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        item {
            //画像
            ImageItem(pokemonDetailData.imageUrl)
            //No 名前
            NameItem(pokemonDetailData.no, pokemonDetailData.name)
            //高さ　重さ
            BodyItem(pokemonDetailData.height, pokemonDetailData.weight)
        }
        items(pokemonDetailData.abilities) { item ->
            //特性
            AbilityItem(item.name, item.hiddenAbility)
        }
        item {
            //タイプ
            TypeItem(typeData = pokemonDetailData.types)
        }
    }

}

@Composable
fun ImageItem(
    imageUrl: String?
) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        AsyncImage(
            model = imageUrl, contentDescription = null, modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(100.dp)
                .border(
                    BorderStroke(4.dp, MaterialTheme.colorScheme.primary),
                    CircleShape
                )
                .padding(4.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun NameItem(
    no: Int,
    name: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = "No.$no",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = name,
            fontSize = 20.sp,
        )
    }
}

@Composable
fun BodyItem(
    height: Int,
    weight: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .weight(weight = 1f, fill = false)
                .fillMaxWidth(),
        ) {
            Text(
                text = "高さ",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = height.toString(),
                fontSize = 20.sp,
            )
        }
        Column(
            modifier = Modifier
                .weight(weight = 1f, fill = false)
                .fillMaxWidth(),
        ) {
            Text(
                text = "重さ",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = weight.toString(),
                fontSize = 20.sp,
            )
        }

    }
}

@Composable
fun AbilityItem(
    name: String,
    hiddenAbility: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = if (hiddenAbility) "夢特性" else "通常特性",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = name,
            fontSize = 20.sp,
        )
    }
}

@Composable
fun TypeItem(
    typeData: List<TypeData>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = "タイプ",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(typeData) {
                TypeCard(it.name)
            }
        }
    }
}

@Composable
fun TypeCard(name: String) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Text(
            text = name,
            fontSize = 20.sp,
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

@Preview
@Composable
fun PreNameItem(
) {
    NameItem(10, "フシギバナ")
}

@Preview
@Composable
fun PreBodyItem(
) {
    BodyItem(10, 10)
}

@Preview
@Composable
fun PreAbilityItem(
) {
    Column() {
        AbilityItem("せいちょう", false)
        AbilityItem("ようりょく", true)
    }

}

@Preview
@Composable
fun PreTypeCard(
) {
    val types: List<String> = listOf("Normal", "Fire")
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(types) {
            TypeCard(it)
        }
    }

}