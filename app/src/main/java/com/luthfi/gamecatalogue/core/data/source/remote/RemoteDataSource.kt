package com.luthfi.gamecatalogue.core.data.source.remote

import android.util.Log
import com.luthfi.gamecatalogue.core.data.source.remote.network.ApiResponse
import com.luthfi.gamecatalogue.core.data.source.remote.network.ApiService
import com.luthfi.gamecatalogue.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    suspend fun getGameList(): Flow<ApiResponse<List<GameResponse>>>
        = flow {
            try {
                val response = apiService.getGameList()
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
}