package com.example.mbregistrationapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MBulakApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MBulakApp)
            inject()
        }
    }

    private fun inject() = loadKoinModules

    private val loadKoinModules by lazy {
        loadKoinModules(listOf(viewModelModule, repositoryModule, networkRepository))
    }
}
