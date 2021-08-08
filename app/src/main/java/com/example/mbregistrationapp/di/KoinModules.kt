package com.example.mbregistrationapp.di

import com.example.mbregistrationapp.network.RetrofitClient
import com.example.mbregistrationapp.network.provideHttpLoginingInterceptor
import com.example.mbregistrationapp.network.provideOkHttpClient
import com.example.mbregistrationapp.network.provideRegistrationApi
import com.example.mbregistrationapp.repository.RegistrationFormRepository
import com.example.mbregistrationapp.repository.RegistrationFormRepositoryImpl
import com.example.mbregistrationapp.repository.RegistrationRepository
import com.example.mbregistrationapp.repository.RegistrationRepositoryImpl
import com.example.mbregistrationapp.ui.activity.MainViewModel
import com.example.mbregistrationapp.ui.fragment.registration.RegistrationViewModel
import com.example.mbregistrationapp.ui.fragment.registrtationform.RegistrationFormViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitClient(get()) }
    single { provideOkHttpClient(get()) }
    single { provideHttpLoginingInterceptor() }
    single { provideRegistrationApi(get()) }
    single<RegistrationRepository> { RegistrationRepositoryImpl(get()) }
    single<RegistrationFormRepository> { RegistrationFormRepositoryImpl(get()) }
    viewModel { MainViewModel() }
    viewModel { RegistrationViewModel(get()) }
    viewModel { RegistrationFormViewModel() }
}