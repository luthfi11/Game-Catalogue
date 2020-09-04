package com.luthfi.gamecatalogue.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthfi.gamecatalogue.core.data.source.remote.network.ApiResponse
import com.luthfi.gamecatalogue.core.data.source.remote.network.ApiService
import com.luthfi.gamecatalogue.core.data.source.remote.response.GameResponse
import com.luthfi.gamecatalogue.core.data.source.remote.response.ListGameResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getGameList(): LiveData<ApiResponse<List<GameResponse>>> {
        val  resultData = MutableLiveData<ApiResponse<List<GameResponse>>>()

        val client = apiService.getGameList()
        client.enqueue(object : Callback<ListGameResponse> {
            override fun onResponse(
                call: Call<ListGameResponse>,
                response: Response<ListGameResponse>
            ) {
                val dataArray = response.body()?.results
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<ListGameResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }

        })

        return resultData
    }
}