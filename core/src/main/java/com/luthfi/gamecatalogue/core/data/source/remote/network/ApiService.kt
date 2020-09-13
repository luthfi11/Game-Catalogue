package com.luthfi.gamecatalogue.core.data.source.remote.network

import com.luthfi.gamecatalogue.core.data.source.remote.response.GameResponse
import com.luthfi.gamecatalogue.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games?page_size=15")
    suspend fun getPopularGames(): ListGameResponse

    @GET("games?ordering=released&page_size=15")
    suspend fun getUpcomingGames(): ListGameResponse

    @GET("games?ordering=-rating&page_size=15")
    suspend fun getTopRatedGames(): ListGameResponse

    @GET("games")
    suspend fun searchGames(@Query("search") name: String): ListGameResponse

    @GET("games/{id}")
    suspend fun getGameDetail(@Path("id") id: String): GameResponse
}