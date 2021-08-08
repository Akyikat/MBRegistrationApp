package com.example.mbregistrationapp

import android.app.Application
import com.example.mbregistrationapp.di.networkModule
import com.example.mbregistrationapp.storage.AppPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MBulakApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MBulakApp)
            inject()
        }
        AppPreferences.init(this)
    }

    private fun inject() = loadKoinModules

    private val loadKoinModules by lazy {
        loadKoinModules(listOf(networkModule))
    }
}
