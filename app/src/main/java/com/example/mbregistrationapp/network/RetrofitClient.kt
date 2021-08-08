package com.example.mbregistrationapp.network

import com.example.mbregistrationapp.data.RegistrationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(private val okHttpClient: OkHttpClient) {

    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object {
        private const val BASE_URL = "https://test.dengi-credit.org/api/app/"
    }
}

fun provideRegistrationApi(retrofit: RetrofitClient) =
    retrofit.provideRetrofit().create(RegistrationApi::class.java)

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return UnsafeOkHttpClient.getUnsafeOkHttpClient()
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun provideHttpLoginingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
