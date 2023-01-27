package com.fakhrirasyids.beritain

import android.app.Application
import com.fakhrirasyids.beritain.di.useCaseModule
import com.fakhrirasyids.beritain.di.viewModelModule
import com.fakhrirasyids.core.di.databaseModule
import com.fakhrirasyids.core.di.networkModule
import com.fakhrirasyids.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
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