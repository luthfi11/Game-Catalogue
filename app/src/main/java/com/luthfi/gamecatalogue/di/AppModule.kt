package com.luthfi.gamecatalogue.di

import com.luthfi.gamecatalogue.core.domain.usecase.GameInteractor
import com.luthfi.gamecatalogue.core.domain.usecase.GameUseCase
import com.luthfi.gamecatalogue.detail.GameDetailViewModel
import com.luthfi.gamecatalogue.explore.ExploreViewModel
import com.luthfi.gamecatalogue.favorite.FavoriteViewModel
import com.luthfi.gamecatalogue.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { ExploreViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { GameDetailViewModel(get()) }
}