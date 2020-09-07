package com.luthfi.gamecatalogue.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.luthfi.gamecatalogue.core.domain.usecase.GameUseCase

class HomeViewModel(gameUseCase: GameUseCase) : ViewModel() {
    val game = gameUseCase.getGameList().asLiveData()
}