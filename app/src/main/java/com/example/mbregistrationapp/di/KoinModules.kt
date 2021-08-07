package com.example.mbregistrationapp.di
import com.example.mbregistrationapp.network.RetrofitClient
import com.example.mbregistrationapp.network.provideHttpLoginingInterceptor
import com.example.mbregistrationapp.network.provideOkHttpClient
import com.example.mbregistrationapp.network.provideRegistrationApi
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitClient(get()) }
    single { provideOkHttpClient(get()) }
    single { provideHttpLoginingInterceptor() }
    single { provideRegistrationApi(get()) }
}