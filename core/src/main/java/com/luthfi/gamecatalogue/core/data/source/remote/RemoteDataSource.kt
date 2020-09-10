package com.luthfi.gamecatalogue.core.data.source.remote

import android.util.Log
import com.luthfi.gamecatalogue.core.data.source.remote.network.ApiResponse
import com.luthfi.gamecatalogue.core.data.source.remote.network.ApiService
import com.luthfi.gamecatalogue.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getPopularGames(): Flow<ApiResponse<List<GameResponse>>> = flow {
        try {
            val response = apiService.getPopularGames()
            val dataArray = response.results
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.results))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.message.toString())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getUpcomingGames(): Flow<ApiResponse<List<GameResponse>>> = flow {
        try {
            val response = apiService.getUpcomingGames()
            val dataArray = response.results
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.results))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.message.toString())
        }
    }.flowOn(Dispatchers.IO)

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