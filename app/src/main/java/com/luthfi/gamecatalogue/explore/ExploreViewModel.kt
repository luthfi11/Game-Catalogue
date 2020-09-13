package com.luthfi.gamecatalogue.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.luthfi.gamecatalogue.core.domain.usecase.GameUseCase

class ExploreViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun searchGames(name: String) = gameUseCase.searchGames(name).asLiveData()
}