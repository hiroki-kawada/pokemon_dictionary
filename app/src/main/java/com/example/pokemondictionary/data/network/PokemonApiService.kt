package com.example.pokemondictionary.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * ポケモンAPI処理IF
 */
interface PokemonApiService {
    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): PokemonListResponse

    @GET("pokemon/{no}")
    suspend fun getPokemonDetail(
        @Path("no") no: Int,
    ): PokemonDetailResponse
}

// BASEURL
private const val BASE_URL =
    "https://pokeapi.co/api/v2/"

/**
 * レスポンスデータに未定義のキーがJSONに存在しても無視するように「ignoreUnknownKeys」をtrueに設定
 */
private val json = Json { ignoreUnknownKeys = true }

/**
 * Retrofit生成
 */
@OptIn(ExperimentalSerializationApi::class)
private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL).build()

/**
 * retrofitService
 */
object PokemonApi {
    val retrofitService: PokemonApiService by lazy { retrofit.create(PokemonApiService::class.java) }
}