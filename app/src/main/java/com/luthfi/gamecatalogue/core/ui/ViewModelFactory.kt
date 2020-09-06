package com.luthfi.gamecatalogue.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luthfi.gamecatalogue.core.di.Injection
import com.luthfi.gamecatalogue.core.domain.usecase.GameUseCase
import com.luthfi.gamecatalogue.detail.GameDetailViewModel
import com.luthfi.gamecatalogue.explore.ExploreViewModel
import com.luthfi.gamecatalogue.favorite.FavoriteViewModel
import com.luthfi.gamecatalogue.home.HomeViewModel

class ViewModelFactory private constructor(private val gameUseCase: GameUseCase) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideGameUseCase(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(gameUseCase) as T
            }
            modelClass.isAssignableFrom(ExploreViewModel::class.java) -> {
                ExploreViewModel(gameUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(gameUseCase) as T
            }
            modelClass.isAssignableFrom(GameDetailViewModel::class.java) -> {
                GameDetailViewModel(gameUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}