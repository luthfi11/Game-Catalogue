package com.luthfi.gamecatalogue.explore

import androidx.lifecycle.*
import com.luthfi.gamecatalogue.core.domain.usecase.GameUseCase

class ExploreViewModel(private val gameUseCase: GameUseCase) : ViewModel() {

    private val searchQuery = MutableLiveData("")

    val searchGames = Transformations.switchMap(searchQuery) {
        gameUseCase.searchGames(it).asLiveData()
    }

    fun setSearchQuery(name: String) {
        searchQuery.value = name
    }
}