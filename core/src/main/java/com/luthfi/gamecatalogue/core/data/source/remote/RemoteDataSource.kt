package com.luthfi.gamecatalogue.core.data.source.remote

import android.util.Log
import com.luthfi.gamecatalogue.core.data.source.remote.network.ApiResponse
import com.luthfi.gamecatalogue.core.data.source.remote.network.ApiService
import com.luthfi.gamecatalogue.core.data.source.remote.response.GameResponse
import com.luthfi.gamecatalogue.core.data.source.remote.response.ListGameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    private suspend fun requestGameData(response: ListGameResponse) =flow {
        try {
            val dataArray = response.results
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.results))
                Log.e("RemoteDataSource", response.results.toString())
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.message.toString())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getPopularGames(): Flow<ApiResponse<List<GameResponse>>> {
        val response = apiService.getPopularGames()
        return requestGameData(response)
    }

    suspend fun getUpcomingGames(): Flow<ApiResponse<List<GameResponse>>> {
        val response = apiService.getUpcomingGames()
        return requestGameData(response)
    }

    suspend fun getTopRatedGames(): Flow<ApiResponse<List<GameResponse>>> {
        val response = apiService.getTopRatedGames()
        return requestGameData(response)
    }

    suspend fun searchGames(name: String): Flow<ApiResponse<List<GameResponse>>> {
        val response = apiService.searchGames(name)
        return requestGameData(response)
    }

    suspend fun getGameDetail(id: String): Flow<ApiResponse<GameResponse>> = flow {
        try {
            val response = apiService.getGameDetail(id)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.message.toString())
        }
    }.flowOn(Dispatchers.IO)
}