package com.luthfi.gamecatalogue

import android.app.Application
import com.luthfi.gamecatalogue.core.di.databaseModule
import com.luthfi.gamecatalogue.core.di.networkModule
import com.luthfi.gamecatalogue.core.di.repositoryModule
import com.luthfi.gamecatalogue.di.useCaseModule
import com.luthfi.gamecatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}