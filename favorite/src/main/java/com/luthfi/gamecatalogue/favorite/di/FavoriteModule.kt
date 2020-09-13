package com.luthfi.gamecatalogue.favorite.di

import com.luthfi.gamecatalogue.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}