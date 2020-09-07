package com.luthfi.gamecatalogue.core.data.source.remote.network

import com.luthfi.gamecatalogue.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET

interface ApiService {

    @GET("games?page_size=30")
    suspend fun getGameList(): ListGameResponse

}